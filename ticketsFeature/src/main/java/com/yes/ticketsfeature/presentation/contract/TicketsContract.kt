package com.yes.ticketsfeature.presentation.contract

import com.yes.sharedmodule.presentation.ui.UiEffect
import com.yes.sharedmodule.presentation.ui.UiEvent
import com.yes.sharedmodule.presentation.ui.UiState
import com.yes.ticketsfeature.presentation.model.TicketsUi

class TicketsContract {

        sealed class Event : UiEvent {

        }
        data class State(
            val state: TicketsState,
            val tickets: TicketsUi?=null,
        ) : UiState

        sealed class TicketsState {
            data object Idle : TicketsState()
            data object Loading : TicketsState()
            data object Success: TicketsState()

        }
        sealed class Effect : UiEffect {
            data object UnknownException : Effect()
        }


}