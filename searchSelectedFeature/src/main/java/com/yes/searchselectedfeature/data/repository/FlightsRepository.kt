package com.yes.searchselectedfeature.data.repository

import com.yes.searchselectedfeature.data.mapper.Mapper
import com.yes.searchselectedfeature.domain.model.Flight
import com.yes.searchselectedfeature.domain.repository.IFlightsRepository

class FlightsRepository(
    private val flightApi: FlightApi,
    private val mapper: Mapper
):IFlightsRepository {
    override suspend fun getFlights(): List<Flight>? {
        return flightApi.getOffers()
            .body()
            ?.tickets_offers
            ?.map {
                mapper.map(it)
            }
    }
}