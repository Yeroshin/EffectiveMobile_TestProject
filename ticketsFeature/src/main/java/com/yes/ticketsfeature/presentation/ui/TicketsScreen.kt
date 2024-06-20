package com.yes.ticketsfeature.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
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
import com.yes.ticketsfeature.presentation.model.TicketsUi


class TicketsScreen: BaseFragment() {
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

    }
    private val adapter by lazy {
        ListDelegationAdapter(
            offerAdapterDelegate(),
        )
    }
    private fun offerAdapterDelegate() =
        adapterDelegateViewBinding<TicketsUi, IAdapterDelegate, TicketsItemBinding>(
            { layoutInflater, root -> TicketsItemBinding.inflate(layoutInflater, root, false) }
        ) {

            bind {

            }
        }

    override fun showEffect() {

    }
}