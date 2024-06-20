package com.yes.mainfeature.presentation.model

import com.yes.sharedmodule.presentation.model.IAdapterDelegate

data class OfferUi (
    val image:Int,
    val title: String,
    val town: String,
    val price:String
): IAdapterDelegate