package com.yes.mainfeature.data.repository

import com.yes.mainfeature.data.model.Offers
import retrofit2.Response
import retrofit2.http.GET

interface TestApi {
  /*  @GET("/todos")
    suspend fun getTodos(): Response<List<TmpEntity>>*/

    @GET("/v3/214a1713-bac0-4853-907c-a1dfc3cd05fd")
    suspend fun getTodos(): Response<Offers>
}