package com.yes.searchselectedfeature.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yes.searchselectedfeature.domain.usecase.GetFlightsUseCase
import com.yes.searchselectedfeature.presentation.contract.SearchSelectedContract
import com.yes.sharedmodule.presentation.vm.BaseViewModel
import com.yes.searchselectedfeature.presentation.contract.SearchSelectedContract.Event
import com.yes.searchselectedfeature.presentation.contract.SearchSelectedContract.State
import com.yes.searchselectedfeature.presentation.contract.SearchSelectedContract.Effect
import com.yes.searchselectedfeature.presentation.mapper.MapperUi

class SearchSelectedViewModel(
    private val mapper: MapperUi,
    private val getFlightsUseCase : GetFlightsUseCase
): BaseViewModel<Event, State, Effect>()  {
    init {
        getFlights()
    }
    override fun createInitialState(): State {
        return State(
            SearchSelectedContract.SearchSelectedState.Idle
        )
    }

    override fun handleEvent(event: Event) {
        when (event) {
            is Event.OnDepartureTimeEntered -> setState {
                copy(
                    state = SearchSelectedContract.SearchSelectedState.Idle,
                    departureTime = event.departureTime
                )
            }
        }
    }
    private fun getFlights(){
        withUseCaseScope(
            //  loadingUpdater = { isLoading -> updateUiState { copy(isLoading = isLoading) } },
            onError = { println(it.message) },
            block = {
                val flights = getFlightsUseCase()
                setState {
                    copy(
                        state = SearchSelectedContract.SearchSelectedState.Success,
                        flights = flights?.map {
                            mapper.map(it)
                        }
                    )
                }
            }
        )
    }
    class Factory(
        private val mapperUi: MapperUi,
        private val getFlightsUseCase : GetFlightsUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return SearchSelectedViewModel(
                mapperUi,
                getFlightsUseCase
            ) as T
        }
    }
}