package com.yes.mainfeature.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yes.mainfeature.data.mapper.Mapper
import com.yes.mainfeature.domain.usecase.GetDepartureUseCase
import com.yes.mainfeature.domain.usecase.GetOffersUseCase
import com.yes.mainfeature.domain.usecase.SaveArrivalUseCase
import com.yes.mainfeature.domain.usecase.SaveDepartureUseCase
import com.yes.mainfeature.presentation.contract.MainContract
import com.yes.sharedmodule.presentation.vm.BaseViewModel
import com.yes.mainfeature.presentation.contract.MainContract.State
import com.yes.mainfeature.presentation.contract.MainContract.Effect
import com.yes.mainfeature.presentation.contract.MainContract.Event
import com.yes.mainfeature.presentation.mapper.UiMapper

class MainViewModel(
    private val saveArrivalUseCase: SaveArrivalUseCase,
    private val getDepartureUseCase: GetDepartureUseCase,
    private val saveDepartureUseCase: SaveDepartureUseCase,
    private val getOffersUseCase: GetOffersUseCase,
    private val mapper: UiMapper
) : BaseViewModel<Event, State, Effect>() {

    init {
        getOffers()
        getDeparture()
    }

    private fun getOffers() {
        withUseCaseScope(
            //  loadingUpdater = { isLoading -> updateUiState { copy(isLoading = isLoading) } },
            onError = { println(it.message) },
            block = {
                val offers = getOffersUseCase()
                setState {
                    copy(
                        state = MainContract.MainState.Success,
                        offers = offers?.map {
                            mapper.map(it)
                        }
                    )
                }
            }
        )
    }

    override fun createInitialState(): State {
        return State(
            MainContract.MainState.Idle
        )
    }

    override fun handleEvent(event: Event) {
        when (event) {
            Event.OnGetOffers -> getOffers()
            is Event.OnDepartureEntered -> saveDeparture(event.departure)
            is Event.OnArrivalEntered -> saveArrival(event.arrival)
        }
    }
    private fun getDeparture(){
        withUseCaseScope(
            //  loadingUpdater = { isLoading -> updateUiState { copy(isLoading = isLoading) } },
            onError = { println(it.message) },
            block = {
                val departure=getDepartureUseCase()
                setState {
                    copy(
                        state = MainContract.MainState.Success,
                        departure = departure
                      )
                }
            }
        )
    }

    private fun saveDeparture(departure: String) {
        withUseCaseScope(
            //  loadingUpdater = { isLoading -> updateUiState { copy(isLoading = isLoading) } },
            onError = { println(it.message) },
            block = {
               saveDepartureUseCase(
                   SaveDepartureUseCase.Params(departure)
               )
            }
        )
    }
    private fun saveArrival(arrival:String){
        withUseCaseScope(
            //  loadingUpdater = { isLoading -> updateUiState { copy(isLoading = isLoading) } },
            onError = { println(it.message) },
            block = {
                saveArrivalUseCase(
                    SaveArrivalUseCase.Params(arrival)
                )
            }
        )
    }

    class Factory(
        private val saveArrivalUseCase: SaveArrivalUseCase,
        private val getDepartureUseCase: GetDepartureUseCase,
        private val saveDepartureUseCase: SaveDepartureUseCase,
        private val getOffersUseCase: GetOffersUseCase,
        private val mapper: UiMapper
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                saveArrivalUseCase,
                getDepartureUseCase,
                saveDepartureUseCase,
                getOffersUseCase,
                mapper
            ) as T
        }
    }
}