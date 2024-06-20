package com.yes.ticketsfeature.domain.repository

import com.yes.ticketsfeature.domain.model.Ticket
import com.yes.ticketsfeature.domain.model.Tickets

interface ITicketsRepository {
    suspend fun getTickets():Tickets?
}