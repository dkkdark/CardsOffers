package com.kseniabl.cardsmarket.ui.base

import com.kseniabl.cardsmarket.ui.all_prods.CardModel
import com.kseniabl.cardsmarket.ui.all_prods.ExecutorModel

interface ItemViewCardModel {
    fun bindItem(item: CardModel)
}