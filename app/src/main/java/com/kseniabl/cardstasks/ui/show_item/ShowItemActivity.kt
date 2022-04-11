package com.kseniabl.cardstasks.ui.show_item

import android.content.Intent
import android.os.Bundle
import androidx.core.view.ViewCompat
import com.kseniabl.cardtasks.R
import com.kseniabl.cardstasks.ui.base.BaseActivity
import com.kseniabl.cardstasks.ui.chat.ChatScreenActivity
import com.kseniabl.cardstasks.ui.main.MainActivity
import com.kseniabl.cardstasks.ui.models.CardModel
import com.kseniabl.cardtasks.ui.show_item.ShowItemPresenterInterface
import com.kseniabl.cardtasks.ui.show_item.ShowItemView
import kotlinx.android.synthetic.main.activity_card_details.*
import javax.inject.Inject

class ShowItemActivity: BaseActivity(), ShowItemView {

    @Inject
    lateinit var presenter: ShowItemPresenterInterface<ShowItemView>

    private var card: CardModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_details)
        presenter.attachView(this)

        val transName = intent.extras?.getString("transName")
        ViewCompat.setTransitionName(headerLayout, transName)

        card = intent.extras?.getSerializable("card") as CardModel
        card?.let {
            setData()
            setListener()
        }

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

    private fun setData() {
        detailTitle.text = card!!.title
        if (card!!.agreement)
            feeText.text = "By agreement"
        else
            feeText.text = card!!.cost.toString()
        untilText.text = card!!.date
        descriptionText.text = card!!.description

        presenter.loadFreelancer(card!!.user_id, nameText, specializationText, itemExeRating)
    }

    private fun setListener() {
        respondToTask.setOnClickListener { openChatScreenActivity() }
    }

    private fun openChatScreenActivity() {
        val intent = Intent(this, ChatScreenActivity::class.java)
        intent.putExtra("id", card!!.user_id)
        intent.putExtra("card_id", card!!.id)
        intent.putExtra("card_title", card!!.title)
        intent.putExtra("card_cost", card!!.cost.toString())
        startActivity(intent)
    }
}