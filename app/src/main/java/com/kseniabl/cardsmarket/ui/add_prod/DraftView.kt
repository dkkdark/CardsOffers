package com.kseniabl.cardsmarket.ui.add_prod

import com.kseniabl.cardsmarket.ui.base.BaseView
import com.kseniabl.cardsmarket.ui.models.CardModel

interface DraftView: BaseView {
    fun showCreateTaskDialog(item: CardModel)
    fun provideAdapter(): DraftAdapter
}