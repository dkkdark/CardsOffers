package com.kseniabl.cardstasks.ui.freelancer_details

import androidx.cardview.widget.CardView
import com.kseniabl.cardstasks.ui.models.CardModel
import com.kseniabl.cardtasks.ui.base.BaseView

interface CardsFreelancerView: BaseView {
    fun openShowItemFragment(item: CardModel)
}