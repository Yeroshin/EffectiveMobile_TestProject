package com.yes.searchselectedfeature.presentation.ui

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.graphics.PorterDuff
import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.yes.searchselectedfeature.R
import com.yes.searchselectedfeature.databinding.SearchSelectedBinding
import com.yes.searchselectedfeature.databinding.SearchSelectedItemBinding
import com.yes.searchselectedfeature.presentation.contract.SearchSelectedContract
import com.yes.searchselectedfeature.presentation.model.FlightUi
import com.yes.sharedmodule.NavCommand
import com.yes.sharedmodule.presentation.model.IAdapterDelegate
import com.yes.sharedmodule.presentation.ui.BaseDependency
import com.yes.sharedmodule.presentation.ui.BaseFragment
import com.yes.sharedmodule.presentation.ui.UiState
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.Locale


class SearchSelectedScreen : BaseFragment() {
    interface DependencyResolver {
        fun resolveSearchSelectedScreenDependency(): BaseDependency
    }

    private val binder by lazy {
        binding as SearchSelectedBinding
    }
    override val dependency by lazy {
        (requireActivity().application as DependencyResolver)
            .resolveSearchSelectedScreenDependency()
    }
    private val adapter by lazy {
        ListDelegationAdapter(
            offerAdapterDelegate(),
        )
    }

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        return SearchSelectedBinding.inflate(inflater, container, false)
    }

    private val myCalendar by lazy {
        Calendar.getInstance()
    }
    private val dateDeparture by lazy {
        OnDateSetListener { view, year, month, day ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, day)
            val dateFormat = SimpleDateFormat("dd MMM, EEE", Locale.getDefault())
            viewModel.setEvent(
                SearchSelectedContract.Event.OnDepartureTimeEntered(departureTime = myCalendar.time)
            )
            binder.departureDate.text = dateFormat.format(myCalendar.time)
        }
    }
    private val dateArrival by lazy {
        OnDateSetListener { view, year, month, day ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, day)
            val dateFormat = SimpleDateFormat("dd MMM, EEE", Locale.getDefault())
            binder.arrivalDate.text = dateFormat.format(myCalendar.time)
        }
    }

    override fun setUpView() {


        (binding as SearchSelectedBinding).searchSelectedDeparture.text =
            Editable.Factory.getInstance().newEditable(
                arguments?.getString("departure")
            )
        (binding as SearchSelectedBinding).searchSelectedArrival.text =
            Editable.Factory.getInstance().newEditable(
                arguments?.getString("arrival")
            )
        (binding as SearchSelectedBinding).swap.setOnClickListener {
            val arrival = binder.searchSelectedArrival.text
            binder.searchSelectedArrival.text = Editable.Factory.getInstance().newEditable(
                binder.searchSelectedDeparture.text
            )
            binder.searchSelectedDeparture.text = Editable.Factory.getInstance().newEditable(
                arrival
            )
        }
        binder.departureDate.text =
            SimpleDateFormat("dd MMM, EEE", Locale.getDefault()).format(Date())


        binder.departureDate.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                dateDeparture,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        binder.arrivalDate.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                dateArrival,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        binder.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        viewModel.setEvent(
            SearchSelectedContract.Event.OnDepartureTimeEntered(departureTime = Date())
        )
        binder.buttonTicketsSearch.setOnClickListener {
            (viewModel.uiState.value as SearchSelectedContract.State).departureTime?.let {
                (activity as NavCommand).navigate(
                    "android-app://ticketsScreen/${binder.searchSelectedDeparture.text}/${binder.searchSelectedArrival.text}/${mapDateToString(it)}/${"1"}"
                )
            }
        }
    }
    private fun mapDateToString(date:Date):String{
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return format.format(date)
    }
    private fun mapStringToDate(date:String):Date{
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return format.parse(date)
    }


    override fun renderUiState(state: UiState) {
        state as SearchSelectedContract.State
        when (state.state) {
            SearchSelectedContract.SearchSelectedState.Idle -> {}
            SearchSelectedContract.SearchSelectedState.Loading -> {}
            SearchSelectedContract.SearchSelectedState.Success -> dataLoaded(state)
        }
    }

    private fun dataLoaded(state: SearchSelectedContract.State) {
        state.flights?.let {
            binder.recyclerView.adapter = adapter
            adapter.items = it
        }
    }

    override fun showEffect() {

    }

    private fun offerAdapterDelegate() =
        adapterDelegateViewBinding<FlightUi, IAdapterDelegate, SearchSelectedItemBinding>(
            { layoutInflater, root ->
                SearchSelectedItemBinding.inflate(
                    layoutInflater,
                    root,
                    false
                )
            }
        ) {

            bind {

                when (absoluteAdapterPosition) {
                    0 -> binding.searchSelectedIcon.setColorFilter(
                        ContextCompat.getColor(
                            context,
                            com.yes.sharedmodule.R.color.Red
                        ), PorterDuff.Mode.SRC_IN
                    )

                    1 -> binding.searchSelectedIcon.setColorFilter(
                        ContextCompat.getColor(
                            context,
                            com.yes.sharedmodule.R.color.Blue_variant
                        ), PorterDuff.Mode.SRC_IN
                    )

                    2 -> binding.searchSelectedIcon.setColorFilter(
                        ContextCompat.getColor(
                            context,
                            com.yes.sharedmodule.R.color.White
                        ), PorterDuff.Mode.SRC_IN
                    )

                }
                binding.searchSelectedTitle.text = item.title
                binding.searchSelectedPrice.text = item.price
                binding.searchSelectedTime.text = item.time

            }
        }

}