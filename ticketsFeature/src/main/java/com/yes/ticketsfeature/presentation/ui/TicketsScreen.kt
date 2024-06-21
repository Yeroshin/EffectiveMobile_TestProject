package com.yes.ticketsfeature.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Visibility
import androidx.viewbinding.ViewBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.yes.sharedmodule.presentation.model.IAdapterDelegate
import com.yes.sharedmodule.presentation.ui.BaseDependency
import com.yes.sharedmodule.presentation.ui.BaseFragment
import com.yes.sharedmodule.presentation.ui.UiState
import com.yes.ticketsfeature.databinding.TicketsBinding
import com.yes.ticketsfeature.databinding.TicketsItemBinding
import com.yes.ticketsfeature.presentation.contract.TicketsContract
import com.yes.ticketsfeature.presentation.model.TicketUi
import com.yes.ticketsfeature.presentation.model.TicketsUi
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date


class TicketsScreen : BaseFragment() {
    interface DependencyResolver {
        fun resolveTicketsDependency(): BaseDependency
    }

    override val dependency by lazy {
        (requireActivity().application as DependencyResolver)
            .resolveTicketsDependency()
    }
    private val binder by lazy {
        binding as TicketsBinding
    }

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        return TicketsBinding.inflate(inflater, container, false)
    }

    override fun setUpView() {

        (binding as TicketsBinding).recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        (binding as TicketsBinding).recyclerView.adapter = adapter
        val date = mapStringToDate(
            requireArguments().getString("departure_date")!!
        )
        val dateString = mapDateToString(date)


        (binding as TicketsBinding).route.text =
            arguments?.getString("departure") + "-" + arguments?.getString("arrival")
        (binding as TicketsBinding).info.text =
            dateString + ", " + arguments?.getString("pass_count") + " пассажир"
        (binding as TicketsBinding).back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun mapDateToString(date: Date): String {
        val format = SimpleDateFormat("dd MMMM")
        return format.format(date)
    }

    private fun mapStringToDate(date: String): Date {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return format.parse(date)
    }

    override fun renderUiState(state: UiState) {
        state as TicketsContract.State
        when (state.state) {
            is TicketsContract.TicketsState.Idle -> {}
            TicketsContract.TicketsState.Loading -> {}
            TicketsContract.TicketsState.Success -> dataLoaded(state)
        }
    }

    private fun dataLoaded(state: TicketsContract.State) {
        state.tickets?.let {
            (binding as TicketsBinding).recyclerView.adapter = adapter
            adapter.items = it
        }
    }

    private val adapter by lazy {
        ListDelegationAdapter(
            offerAdapterDelegate(),
        )
    }

    private fun offerAdapterDelegate() =
        adapterDelegateViewBinding<TicketUi, IAdapterDelegate, TicketsItemBinding>(
            { layoutInflater, root -> TicketsItemBinding.inflate(layoutInflater, root, false) }
        ) {

            bind {
                if (item.badge==null){
                    binding.badge.visibility = View.INVISIBLE
                }else{
                    binding.badge.visibility = View.VISIBLE
                    binding.badgeText.text = item.badge
                }
                binding.price.text = item.price
                binding.departureTime.text = item.departureTime
                binding.arrivalTime.text = item.arrivalTime
                binding.departureAirport.text=item.departureAirport
                binding.arrivalAirport.text=item.arrivalAirport
                binding.transfer.text=item.hasTransfer
                binding.flightTiime.text=item.flightTime
            }
        }

    override fun showEffect() {

    }
}