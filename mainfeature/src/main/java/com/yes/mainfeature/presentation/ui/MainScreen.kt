package com.yes.mainfeature.presentation.ui

import android.content.Context
import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.yes.mainfeature.databinding.MainBinding
import com.yes.mainfeature.databinding.OfferItemBinding
import com.yes.mainfeature.presentation.contract.MainContract
import com.yes.sharedmodule.presentation.model.IAdapterDelegate
import com.yes.mainfeature.presentation.model.OfferUi
import com.yes.sharedmodule.NavCommand
import com.yes.sharedmodule.presentation.ui.BaseDependency
import com.yes.sharedmodule.presentation.ui.BaseFragment
import com.yes.sharedmodule.presentation.ui.UiState


class MainScreen : BaseFragment() {
    interface DependencyResolver {
        fun resolveMainDependency(): BaseDependency
    }


    override val dependency by lazy {
        (requireActivity().application as DependencyResolver)
            .resolveMainDependency()
    }
    private val binder by lazy {
        binding as MainBinding
    }

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBinding {
        return MainBinding.inflate(inflater, container, false)
    }


    override fun setUpView() {

        (binding as MainBinding).departure.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val inputText = binder.departure.text.toString()
                val regex = Regex("[А-Яа-яЁё -]+")
                return@setOnEditorActionListener if (inputText.isNotBlank() && inputText.matches(
                        regex
                    )
                ) {
                    binder.departure.error =null
                    viewModel.setEvent(MainContract.Event.OnDepartureEntered(inputText))

                    false
                } else {
                    binder.departure.error = "Введите только русские буквы"
                    true
                }

            } else {
                true
            }
        }
        (binding as MainBinding).arrival.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                val departure = binder.departure.text
                (activity as NavCommand).navigate(
                    "android-app://com.yes.searchfeature.presentation.ui.SearchScreen/$departure"
                )
            }
        }
        (binding as MainBinding).arrival.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val inputText = binder.departure.text.toString()
                val regex = Regex("[А-Яа-яЁё -]+")
                return@setOnEditorActionListener if (inputText.isNotBlank() && inputText.matches(
                        regex
                    )
                ) {
                    //    viewModel.setEvent(MainContract.Event.OnArrivalEntered(inputText))
                    /*   val request = NavDeepLinkRequest.Builder
                           .fromUri("android-app://com.yes.searchfeature.presentation.ui.SearchScreen".toUri())
                           .build()
                       findNavController().navigate(request)*/

                    false
                } else {
                    binder.departure.error = "Введите только русские буквы"
                    true
                }

            } else {
                true
            }
        }

        (binding as MainBinding).RecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    }

    override fun renderUiState(state: UiState) {
        state as MainContract.State
        when (state.state) {
            is MainContract.MainState.Idle -> {}
            MainContract.MainState.Loading -> {}
            MainContract.MainState.Success -> dataLoaded(state)
        }
    }

    private fun dataLoaded(state: MainContract.State) {
        state.offers?.let {
            (binding as MainBinding).RecyclerView.adapter = adapter
            adapter.items = it
        }
        state.departure?.let {
            binder.departure.text = Editable.Factory.getInstance().newEditable(it)
        }
    }

    override fun showEffect() {

    }

    private var fragmentActivity: FragmentActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentActivity) {
            fragmentActivity = context
        }
    }

    private val adapter by lazy {
        ListDelegationAdapter(
            offerAdapterDelegate(),
        )
    }

    private fun offerAdapterDelegate() =
        adapterDelegateViewBinding<OfferUi, IAdapterDelegate, OfferItemBinding>(
            { layoutInflater, root -> OfferItemBinding.inflate(layoutInflater, root, false) }
        ) {

            bind {
                Glide
                    .with(context)
                    .load(item.image)
                    .fitCenter()
                    .into(binding.offerImage)
                binding.offerTitle.text = item.title
                binding.offerTown.text = item.town
                binding.offerPrice.text = item.price
            }
        }


}