package com.yes.mainfeature.data.repository

import com.yes.mainfeature.data.model.Offers
import retrofit2.Response
import retrofit2.http.GET

interface OfferApi {

    @GET("/v3/ad9a46ba-276c-4a81-88a6-c068e51cce3a")
    suspend fun getOffers():Response<Offers>
}