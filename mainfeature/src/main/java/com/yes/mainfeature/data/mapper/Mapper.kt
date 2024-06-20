package com.yes.mainfeature.data.mapper

import com.yes.mainfeature.data.model.OfferEntity
import com.yes.mainfeature.domain.model.Offer

class Mapper {
    fun map(offerEntity: OfferEntity): Offer {
        return Offer(
            id = offerEntity.id,
            price = offerEntity.price.value,
            title = offerEntity.title,
            town = offerEntity.town
        )
    }
}