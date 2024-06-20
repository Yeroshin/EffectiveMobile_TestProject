package com.yes.searchselectedfeature.presentation.model

import com.yes.sharedmodule.presentation.model.IAdapterDelegate

data class FlightUi (
    val title:String,
    val price:String,
    val time:String
):IAdapterDelegate