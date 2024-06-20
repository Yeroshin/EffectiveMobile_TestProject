package com.yes.ticketsfeature.domain.usecase

import com.yes.sharedmodule.domain.UseCase
import com.yes.ticketsfeature.data.repository.TicketsRepository
import com.yes.ticketsfeature.domain.model.Tickets
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first

class GetTicketsUseCase(
    dispatcher: CoroutineDispatcher,
    private val ticketsRepository: TicketsRepository
) : UseCase<Unit?, Tickets?>(dispatcher){
    override suspend fun run(params: Unit?): Tickets? {
        return ticketsRepository.getTickets()
    }

}