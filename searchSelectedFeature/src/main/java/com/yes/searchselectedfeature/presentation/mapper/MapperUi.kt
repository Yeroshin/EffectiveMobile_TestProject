package com.yes.searchselectedfeature.presentation.mapper

import com.yes.searchselectedfeature.domain.model.Flight
import com.yes.searchselectedfeature.presentation.model.FlightUi

class MapperUi {
    fun map(flight: Flight):FlightUi{
        return FlightUi(
            flight.title,
            formatNumberWithSpaces(
                flight.price
            ),
            flight.time.joinToString()
        )
    }
    private fun formatNumberWithSpaces(number: Int): String {
        val formattedNumber = String.format("%,d", number).replace(",", " ")
        return "от $formattedNumber \u20BD"
    }
}