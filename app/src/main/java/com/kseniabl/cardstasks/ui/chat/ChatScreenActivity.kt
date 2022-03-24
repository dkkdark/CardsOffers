package com.kseniabl.cardstasks.ui.chat

import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
        setObserver()

    }

    companion object {
        fun newInstance(): ChatScreenActivity = ChatScreenActivity()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    private fun setPreviousMessages() {
        /*val messages = MessagesContainer.getMessages()
        for (m in messages) {
            chatView.addMessage(m)
        }*/
        presenter.setAllMessages(currentUserClass.readSharedPref().id, chatView)
    }

    private fun setObserver() {
        MessagesContainer.getIsMessageAppearObservable().subscribe(object : Observer<ChatMessage> {
            override fun onSubscribe(d: Disposable?) {
            }

            override fun onNext(m: ChatMessage) {
                Log.e("qqq", "onNext")
                runOnUiThread {
                    chatView.addMessage(m)
                }
            }

            override fun onError(e: Throwable?) {
                Log.e("qqq", "onError ChatScreenActivity ${e?.message}")
            }

            override fun onComplete() {
            }

        })
    }

    private fun setupSendMessageListener() {
        chatView.setOnSentMessageListener {
            if (id != null) {
                presenter.sentMessageWithServer(id!!, currentUserClass.readSharedPref().username, it.message)
                presenter.insertMessage(currentUserClass.readSharedPref().id, it, chatView)
                true
            }

            else {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                false
            }
        }
    }
}