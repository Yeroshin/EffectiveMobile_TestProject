package com.yes.mainfeature.presentation.mapper


import com.yes.mainfeature.R
import com.yes.mainfeature.domain.model.Offer
import com.yes.mainfeature.presentation.model.OfferUi

class UiMapper {
    fun map(offer: Offer):OfferUi{

        return OfferUi(
            image = when(offer.id){
                1 -> R.drawable.id1
                2 -> R.drawable.id2
                3 -> R.drawable.id3
                else -> 1
            },
            price =formatNumberWithSpaces(
                offer.price
            ) ,
            title = offer.title,
            town = offer.town
        )
    }
    private fun formatNumberWithSpaces(number: Int): String {
        val formattedNumber = String.format("%,d", number).replace(",", " ")
        return "от $formattedNumber \u20BD"
    }
}