package com.yes.searchselectedfeature.data.mapper

import com.yes.searchselectedfeature.data.model.TicketsOffer
import com.yes.searchselectedfeature.domain.model.Flight

class Mapper {
    fun map(flight:TicketsOffer): Flight {
        return Flight(
            flight.title,
            flight.price.value,
            flight.time_range
        )
    }
}