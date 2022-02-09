package com.kseniabl.cardsmarket.ui.all_prods

import androidx.cardview.widget.CardView
import com.kseniabl.cardsmarket.ui.base.BaseView

interface AllProdsView: BaseView {
    fun provideAdapter(): AllProdsAdapter
    fun openShowItemFragment(item: CardModel, image: CardView)
}