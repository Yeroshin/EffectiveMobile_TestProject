package com.yes.searchselectedfeature.presentation.contract

import com.yes.searchselectedfeature.presentation.model.FlightUi
import com.yes.sharedmodule.presentation.ui.UiEffect
import com.yes.sharedmodule.presentation.ui.UiEvent
import com.yes.sharedmodule.presentation.ui.UiState
import java.util.Date

class SearchSelectedContract {
    sealed class Event : UiEvent {
        data class OnDepartureTimeEntered(val departureTime: Date): Event()
    }
    data class State(
        val state:SearchSelectedState,
        val flights:List<FlightUi>?=null,
        val departureTime: Date?=null
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