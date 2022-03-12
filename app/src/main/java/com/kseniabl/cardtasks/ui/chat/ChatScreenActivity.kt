package com.kseniabl.cardtasks.ui.chat

import android.os.Bundle
import com.kseniabl.cardtasks.R
import com.kseniabl.cardtasks.ui.base.BaseActivity
import javax.inject.Inject

class ChatScreenActivity: BaseActivity(), ChatScreenView {

    @Inject
    lateinit var presenter: ChatScreenPresenterInterface<ChatScreenView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_chat_screen)
        presenter.attachView(this)
    }

    companion object {
        fun newInstance(): ChatScreenActivity = ChatScreenActivity()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}