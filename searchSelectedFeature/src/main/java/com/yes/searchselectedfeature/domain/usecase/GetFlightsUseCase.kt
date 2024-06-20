package com.yes.searchselectedfeature.domain.usecase

import com.yes.searchselectedfeature.domain.model.Flight
import com.yes.searchselectedfeature.domain.repository.IFlightsRepository
import com.yes.sharedmodule.domain.UseCase
import kotlinx.coroutines.CoroutineDispatcher

class GetFlightsUseCase (
    dispatcher: CoroutineDispatcher,
    private val flightsRepository: IFlightsRepository
) : UseCase<Unit, List<Flight>?>(dispatcher){
    override suspend fun run(params: Unit?): List<Flight>? {
        return flightsRepository.getFlights()
    }
}