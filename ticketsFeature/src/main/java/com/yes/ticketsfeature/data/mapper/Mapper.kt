package com.yes.ticketsfeature.data.mapper

import com.yes.ticketsfeature.data.model.Ticket
import com.yes.ticketsfeature.data.model.TicketsEntity
import com.yes.ticketsfeature.domain.model.Tickets

class Mapper {
    fun map(ticketsEntity: TicketsEntity):Tickets{
        return Tickets(
            ticketsEntity.tickets
        )
    }
}