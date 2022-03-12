package com.kseniabl.cardtasks.ui.main

import androidx.cardview.widget.CardView
import com.kseniabl.cardtasks.ui.base.BaseView
import com.kseniabl.cardtasks.ui.models.CardModel

interface MainView: BaseView {
    fun openShowItemActivity(card: CardModel, cardView: CardView)
    fun openFreelancerDetailsActivity()
}