package com.yes.mainfeature.presentation.contract

import com.yes.mainfeature.presentation.model.OfferUi
import com.yes.sharedmodule.presentation.ui.UiEffect
import com.yes.sharedmodule.presentation.ui.UiEvent
import com.yes.sharedmodule.presentation.ui.UiState

class MainContract {
        sealed class Event : UiEvent {
            data object OnGetOffers : Event()
            data class OnDepartureEntered(val departure:String): Event()
            data class OnArrivalEntered(val arrival:String): Event()
        }
        data class State(
            val state:MainState,
            val offers: List<OfferUi>?=null,
            val departure:String?=null
        ) : UiState

        sealed class MainState {
            data object Idle : MainState()
            data object Loading : MainState()
            data object Success: MainState()

        }
        sealed class Effect : UiEffect {
            data object UnknownException : Effect()
        }

}