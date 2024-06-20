package com.yes.searchselectedfeature.data.repository

import com.yes.searchselectedfeature.data.model.TicketOffers
import retrofit2.Response
import retrofit2.http.GET

interface FlightApi {
    @GET("/v3/38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a")
    suspend fun getOffers(): Response<TicketOffers>

}