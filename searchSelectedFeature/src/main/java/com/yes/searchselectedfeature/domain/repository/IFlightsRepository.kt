package com.yes.searchselectedfeature.domain.repository

import com.yes.searchselectedfeature.domain.model.Flight

interface IFlightsRepository {
    suspend fun getFlights():List<Flight>?
}