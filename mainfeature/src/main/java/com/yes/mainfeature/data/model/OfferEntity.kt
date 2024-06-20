package com.yes.mainfeature.data.model

data class OfferEntity(
    val id: Int,
    val title: String,
    val town: String,
    val price: Price,
)