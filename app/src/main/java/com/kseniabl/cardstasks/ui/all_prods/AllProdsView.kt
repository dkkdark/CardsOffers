package com.kseniabl.cardtasks.ui.all_prods

import androidx.cardview.widget.CardView
import com.kseniabl.cardstasks.ui.all_prods.AllProdsAdapter
import com.kseniabl.cardtasks.ui.base.BaseView
import com.kseniabl.cardstasks.ui.models.CardModel

interface AllProdsView: BaseView {
    fun provideAdapter(): AllProdsAdapter
    fun openShowItemFragment(item: CardModel, cardView: CardView)
}