package com.kseniabl.cardsmarket.ui.add_prod

import androidx.cardview.widget.CardView
import com.kseniabl.cardsmarket.ui.base.BaseView
import com.kseniabl.cardsmarket.ui.models.CardModel

interface AddProdView: BaseView {
    fun provideAdapter(): AddProdsAdapter
    fun openShowItemFragment(item: CardModel, image: CardView)
    fun showCreateTaskDialog()
    fun startTransition()
}