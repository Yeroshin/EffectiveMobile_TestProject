package com.yes.searchselectedfeature.presentation.contract

import com.yes.searchselectedfeature.presentation.model.FlightUi
import com.yes.sharedmodule.presentation.ui.UiEffect
import com.yes.sharedmodule.presentation.ui.UiEvent
import com.yes.sharedmodule.presentation.ui.UiState

class SearchSelectedContract {
    sealed class Event : UiEvent {
        data object OnGetOffers : Event()
        data class OnDepartureEntered(val departure:String): Event()
        data class OnArrivalEntered(val arrival:String): Event()
    }
    data class State(
        val state:SearchSelectedState,
        val flights:List<FlightUi>?=null
    ) : UiState

    sealed class SearchSelectedState {
        data object Idle : SearchSelectedState()
        data object Loading : SearchSelectedState()
        data object Success: SearchSelectedState()

    }
    sealed class Effect : UiEffect {
        data object UnknownException : Effect()
    }
}