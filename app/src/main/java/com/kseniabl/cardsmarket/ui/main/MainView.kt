package com.kseniabl.cardsmarket.ui.main

import android.widget.TextView
import androidx.cardview.widget.CardView
import com.kseniabl.cardsmarket.ui.base.BaseView
import com.kseniabl.cardsmarket.ui.models.CardModel

interface MainView: BaseView {
    fun openShowItemActivity(card: CardModel, cardView: CardView)
    fun openFreelancerDetailsActivity()
}