package com.kseniabl.cardstasks.ui.show_item

import android.widget.TextView
import com.idlestar.ratingstar.RatingStarView
import com.kseniabl.cardtasks.ui.base.PresenterInterface
import com.kseniabl.cardtasks.ui.show_item.ShowItemView
import com.mikhaellopez.circularimageview.CircularImageView

interface ShowItemPresenterInterface<V: ShowItemView>: PresenterInterface<V> {
    fun loadFreelancer(id: String, nameText: TextView, specializationText: TextView, itemExeRating: RatingStarView)
    fun setupFreelancerImage(id: String, imageViewProfile: CircularImageView)
}