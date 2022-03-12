package com.kseniabl.cardtasks.ui.all_prods

import androidx.cardview.widget.CardView
import com.kseniabl.cardtasks.ui.base.BaseView
import com.kseniabl.cardtasks.ui.models.CardModel

interface AllProdsView: BaseView {
    fun provideAdapter(): AllProdsAdapter
    fun openShowItemFragment(item: CardModel, cardView: CardView)
}