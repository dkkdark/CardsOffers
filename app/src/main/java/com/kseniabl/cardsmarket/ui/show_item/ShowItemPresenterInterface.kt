package com.kseniabl.cardsmarket.ui.show_item

import android.widget.TextView
import com.idlestar.ratingstar.RatingStarView
import com.kseniabl.cardsmarket.ui.base.PresenterInterface

interface ShowItemPresenterInterface<V: ShowItemView>: PresenterInterface<V> {
    fun loadExecutor(id: String, nameText: TextView, specializationText: TextView, itemExeRating: RatingStarView)
}