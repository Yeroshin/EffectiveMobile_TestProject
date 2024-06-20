package com.yes.ticketsfeature.data.repository

import com.yes.ticketsfeature.data.model.TicketsEntity
import retrofit2.Response
import retrofit2.http.GET

interface TicketsApi {
    @GET("/v3/c0464573-5a13-45c9-89f8-717436748b69")
    suspend fun getOffers(): Response<TicketsEntity>
}