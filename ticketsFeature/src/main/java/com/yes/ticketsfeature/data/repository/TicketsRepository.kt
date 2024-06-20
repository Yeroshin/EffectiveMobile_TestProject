package com.yes.ticketsfeature.data.repository

import com.yes.ticketsfeature.data.mapper.Mapper
import com.yes.ticketsfeature.domain.model.Ticket
import com.yes.ticketsfeature.domain.model.Tickets
import com.yes.ticketsfeature.domain.repository.ITicketsRepository

class TicketsRepository(
    private val ticketsApi: TicketsApi,
    private val mapper: Mapper
) : ITicketsRepository {
    override suspend fun getTickets(): Tickets? {
        return ticketsApi.getOffers()
            .body()?.let {
                mapper.map(it)
            }
    }
}