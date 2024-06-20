package com.yes.mainfeature.data.repository

import com.yes.mainfeature.data.mapper.Mapper
import com.yes.mainfeature.domain.model.Offer
import com.yes.mainfeature.domain.repository.IOfferRepository
import retrofit2.HttpException
import java.io.IOException


class OfferRepository(
    private val offerApi: OfferApi,
    private val mapper: Mapper
) : IOfferRepository {
    override suspend fun getOffers(): List<Offer>? {
        return offerApi.getOffers()
            .body()
            ?.offers
            ?.map {
                mapper.map(it)
            }
    }
}