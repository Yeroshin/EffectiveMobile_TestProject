package com.yes.ticketsfeature.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yes.sharedmodule.presentation.vm.BaseViewModel
import com.yes.ticketsfeature.domain.usecase.GetTicketsUseCase
import com.yes.ticketsfeature.presentation.contract.TicketsContract
import com.yes.ticketsfeature.presentation.contract.TicketsContract.Event
import com.yes.ticketsfeature.presentation.contract.TicketsContract.State
import com.yes.ticketsfeature.presentation.contract.TicketsContract.Effect
import com.yes.ticketsfeature.presentation.mapper.MapperUi

class TicketsViewModel(
    private val mapperUi: MapperUi,
    private val getTicketsUseCase: GetTicketsUseCase
) : BaseViewModel<Event, State, Effect>() {
    init {
        getTickets()
    }

    override fun createInitialState(): State {
        return State(
            TicketsContract.TicketsState.Idle
        )
    }
    private fun getTickets(){
        withUseCaseScope(
            //  loadingUpdater = { isLoading -> updateUiState { copy(isLoading = isLoading) } },
            onError = { println(it.message) },
            block = {
                val tickets=getTicketsUseCase()
                setState {
                    copy(
                        state = TicketsContract.TicketsState.Success,
                        tickets = mapperUi.map(tickets)
                    )
                }
            }
        )
    }


    override fun handleEvent(event: Event) {

    }

    class Factory(
        private val mapperUi: MapperUi,
        private val getTicketsUseCase: GetTicketsUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return TicketsViewModel(
                mapperUi,
                getTicketsUseCase
            ) as T
        }
    }
}