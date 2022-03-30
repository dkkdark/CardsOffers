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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_chat_screen)
        presenter.attachView(this)

        id = intent.extras?.getString("id")

        setPreviousMessages()
        setupSendMessageListener()
        //setObserver()
        id?.let {
            presenter.loadReceived(it, chatView, this)
        }
        Log.e("qqq", "id = $id")
    }

    companion object {
        fun newInstance(): ChatScreenActivity = ChatScreenActivity()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    private fun setPreviousMessages() {
        id?.let { presenter.setAllMessages(it, chatView) }
    }

    private fun setupSendMessageListener() {
        chatView.setOnSentMessageListener {
            if (id != null && currentUserClass.readSharedPref() != null) {
                presenter.sentMessageWithServer(currentUserClass.readSharedPref()!!.id, id!!, currentUserClass.readSharedPref()!!.username, it.message)
                presenter.insertMessage(id!!, it, chatView)
                true
            }

            else {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                false
            }
        }
    }
}