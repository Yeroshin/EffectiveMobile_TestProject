package com.yes.ticketsfeature.presentation.model

import com.yes.sharedmodule.presentation.model.IAdapterDelegate


data class TicketUi(

    val badge: String?,
    val price:String,
    val departureTime:String,
    val arrivalTime:String,
    val departureAirport:String,
    val arrivalAirport:String,
    val hasTransfer:Boolean


):IAdapterDelegate