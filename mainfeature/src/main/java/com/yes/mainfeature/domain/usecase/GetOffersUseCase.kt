package com.yes.mainfeature.domain.usecase

import com.yes.mainfeature.domain.model.Offer
import com.yes.mainfeature.domain.repository.IOfferRepository
import com.yes.sharedmodule.domain.UseCase
import kotlinx.coroutines.CoroutineDispatcher

class GetOffersUseCase(
    dispatcher: CoroutineDispatcher,
    private val offerRepository: IOfferRepository
) :UseCase<Unit, List<Offer>?>(dispatcher){
    override suspend fun run(params: Unit?): List<Offer>? {
        return offerRepository.getOffers()
    }
}