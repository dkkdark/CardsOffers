package com.kseniabl.cardtasks.ui.chat

import com.kseniabl.cardtasks.ui.base.BasePresenter
import javax.inject.Inject

class ChatScreenPresenter<V: ChatScreenView, I: ChatScreenInteractorInterface> @Inject constructor(var interactor: I): BasePresenter<V>(), ChatScreenPresenterInterface<V> {

    override fun sentMessageWithServer(id: String, title: String, body: String) {
        interactor.sendMessage(id, title, body)
    }
}