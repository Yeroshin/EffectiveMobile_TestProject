package com.yes.ticketsfeature.domain.model

import com.yes.ticketsfeature.data.model.Arrival
import com.yes.ticketsfeature.data.model.Departure
import com.yes.ticketsfeature.data.model.HandLuggage
import com.yes.ticketsfeature.data.model.Luggage
import com.yes.ticketsfeature.data.model.PriceX

data class Ticket(
    val arrival: Arrival,
    val badge: String,
    val company: String,
    val departure: Departure,
    val hand_luggage: HandLuggage,
    val has_transfer: Boolean,
    val has_visa_transfer: Boolean,
    val id: Int,
    val is_exchangable: Boolean,
    val is_returnable: Boolean,
    val luggage: Luggage,
    val price: PriceX,
    val provider_name: String
)