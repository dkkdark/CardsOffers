package com.kseniabl.cardsmarket.ui.all_prods

import android.widget.TextView
import androidx.cardview.widget.CardView
import com.kseniabl.cardsmarket.ui.base.BaseView
import com.kseniabl.cardsmarket.ui.models.CardModel

interface AllProdsView: BaseView {
    fun provideAdapter(): AllProdsAdapter
    fun openShowItemFragment(item: CardModel, cardView: CardView)
}