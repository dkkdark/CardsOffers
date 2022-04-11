package com.kseniabl.cardstasks.ui.base

import com.kseniabl.cardstasks.ui.models.CardModel

interface ItemViewCardModel {
    fun bindItem(item: CardModel)
}