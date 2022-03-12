package com.kseniabl.cardtasks.ui.base

import com.kseniabl.cardtasks.ui.models.CardModel

interface ItemViewCardModel {
    fun bindItem(item: CardModel)
}