package com.kseniabl.cardsmarket.ui.base

import com.kseniabl.cardsmarket.ui.models.CardModel

interface ItemViewCardModel {
    fun bindItem(item: CardModel)
}