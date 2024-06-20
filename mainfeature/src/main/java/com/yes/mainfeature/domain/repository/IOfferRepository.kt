package com.yes.mainfeature.domain.repository

import com.yes.mainfeature.domain.model.Offer

interface IOfferRepository {
   suspend fun getOffers(): List<Offer>?
}