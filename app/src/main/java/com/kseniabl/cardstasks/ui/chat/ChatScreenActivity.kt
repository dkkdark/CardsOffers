package com.kseniabl.cardstasks.ui.chat

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import co.intentservice.chatui.models.ChatMessage
import com.kseniabl.cardtasks.R
import com.kseniabl.cardstasks.ui.base.BaseActivity
import com.kseniabl.cardstasks.ui.base.CurrentUserClass
import javax.inject.Inject

import com.kseniabl.cardstasks.ui.base.MessagesContainer
import com.kseniabl.cardstasks.utils.CardTasksUtils
import com.kseniabl.cardtasks.ui.chat.ChatScreenView
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_chat_screen.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ChatScreenActivity: BaseActivity(), ChatScreenView {

    @Inject
    lateinit var presenter: ChatScreenPresenterInterface<ChatScreenView>
    @Inject
    lateinit var currentUserClass: CurrentUserClass

    private var id: String? = null
    private var card_id: String? = null
    private var card_title: String? = null
    private var card_cost: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_chat_screen)
        presenter.attachView(this)

        id = intent.extras?.getString("id")
        card_id = intent.extras?.getString("card_id")
        card_title = intent.extras?.getString("card_title")
        card_cost = intent.extras?.getString("card_cost")

        if (id != null && card_id != null && card_title != null && card_cost != null) {
            CardTasksUtils.setCardId(card_id!!)
            setPreviousMessagesAndData()
            setupSendMessageListener()
            presenter.loadReceived(id!!, card_id!!, chatView, this)
        }
        Log.e("qqq", "id = $id  card_id = $card_id")
    }

    companion object {
        fun newInstance(): ChatScreenActivity = ChatScreenActivity()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    private fun setPreviousMessagesAndData() {
        cardTitleChatScreen.text = card_title
        cardCostChatScreen.text = "$card_cost $"
        presenter.setAllMessages(id!!, card_id!!, chatView)
    }

    private fun setupSendMessageListener() {
        chatView.setOnSentMessageListener {
            if (currentUserClass.readSharedPref() != null) {
                presenter.sentMessageWithServer(currentUserClass.readSharedPref()!!.id, id!!, currentUserClass.readSharedPref()!!.username, it.message, card_id!!, card_title!!, card_cost!!)
                presenter.insertMessage(id!!, card_id!!, it!!, chatView, card_title!!, card_cost!!)
                true
            }

            else {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                false
            }
        }
    }
}