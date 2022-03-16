package com.kseniabl.cardstasks.ui.chat

import android.os.Bundle
import android.widget.Toast
import com.kseniabl.cardtasks.R
import com.kseniabl.cardtasks.ui.base.BaseActivity
import javax.inject.Inject

import com.kseniabl.cardstasks.ui.base.CurrentUser
import com.kseniabl.cardtasks.ui.chat.ChatScreenPresenterInterface
import com.kseniabl.cardtasks.ui.chat.ChatScreenView
import kotlinx.android.synthetic.main.fragment_chat_screen.*


class ChatScreenActivity: BaseActivity(), ChatScreenView {

    @Inject
    lateinit var presenter: ChatScreenPresenterInterface<ChatScreenView>

    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_chat_screen)
        presenter.attachView(this)

        id = intent.extras?.getString("id")

        setupSendMessageListener()
    }

    companion object {
        fun newInstance(): ChatScreenActivity = ChatScreenActivity()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    private fun setupSendMessageListener() {
        chatView.setOnSentMessageListener {
            if (id != null && CurrentUser.getUser()?.username != null) {
                presenter.sentMessageWithServer(id!!, CurrentUser.getUser()!!.username, chatView.typedMessage)
                true
            }
            else {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                false
            }
        }
    }
}