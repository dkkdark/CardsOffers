package com.kseniabl.cardstasks.ui.chat

import co.intentservice.chatui.ChatView
import co.intentservice.chatui.models.ChatMessage
import com.kseniabl.cardstasks.db.MapOfChatModels
import com.kseniabl.cardtasks.ui.base.PresenterInterface
import com.kseniabl.cardtasks.ui.chat.ChatScreenView

interface ChatScreenPresenterInterface<V: ChatScreenView>: PresenterInterface<V> {
    fun sentMessageWithServer(id: String, title: String, body: String)
    fun setAllMessages(id: String, chatView: ChatView)
    fun insertMessage(id: String, msg: ChatMessage, chatView: ChatView)
}