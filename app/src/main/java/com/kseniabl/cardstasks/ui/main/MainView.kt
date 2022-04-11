package com.kseniabl.cardstasks.ui.main

import androidx.cardview.widget.CardView
import com.kseniabl.cardstasks.ui.base.FreelancerModel
import com.kseniabl.cardtasks.ui.base.BaseView
import com.kseniabl.cardstasks.ui.models.CardModel

interface MainView: BaseView {
    fun openShowItemActivity(card: CardModel, cardView: CardView)
    fun openFreelancerDetailsActivity(item: FreelancerModel)
}