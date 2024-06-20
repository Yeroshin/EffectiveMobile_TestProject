package com.yes.ticketsfeature.presentation.mapper

import com.yes.ticketsfeature.domain.model.Tickets
import com.yes.ticketsfeature.presentation.model.TicketsUi

class MapperUi {
    fun map(tickets: Tickets?):TicketsUi{
        return TicketsUi()
    }
}