package com.kseniabl.cardtasks.ui.chat

import com.kseniabl.cardtasks.ui.base.PresenterInterface

interface ChatScreenPresenterInterface<V: ChatScreenView>: PresenterInterface<V> {
    fun sentMessageWithServer(id: String, title: String, body: String)
}