package com.kseniabl.cardsmarket.ui.show_item

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.transition.ChangeBounds
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import com.google.android.material.transition.MaterialContainerTransform
import com.kseniabl.cardsmarket.R
import com.kseniabl.cardsmarket.ui.base.BaseActivity
import com.kseniabl.cardsmarket.ui.base.BaseFragment
import com.kseniabl.cardsmarket.ui.main.MainActivity
import com.kseniabl.cardsmarket.ui.models.CardModel
import kotlinx.android.synthetic.main.activity_card_details.*
import javax.inject.Inject

class ShowItemActivity: BaseActivity(), ShowItemView {

    @Inject
    lateinit var presenter: ShowItemPresenterInterface<ShowItemView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_details)
        presenter.attachView(this)

        val transName = intent.extras?.getString("transName")
        ViewCompat.setTransitionName(headerLayout, transName)

        val card = intent.extras?.getSerializable("card") as CardModel
        setData(card)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }

    private fun setData(card: CardModel) {
        detailTitle.text = card.title
        if (card.agreement)
            feeText.text = "By agreement"
        else
            feeText.text = card.cost.toString()
        untilText.text = card.date
        descriptionText.text = card.description

        presenter.loadFreelancer(card.user_id, nameText, specializationText, itemExeRating)
    }
}