package com.kseniabl.cardstasks.ui.chat

import co.intentservice.chatui.ChatView
import co.intentservice.chatui.models.ChatMessage
import com.kseniabl.cardstasks.db.MapOfChatModels
import com.kseniabl.cardtasks.ui.base.PresenterInterface
import com.kseniabl.cardtasks.ui.chat.ChatScreenView

interface ChatScreenPresenterInterface<V: ChatScreenView>: PresenterInterface<V> {
    fun sentMessageWithServer(senderId: String, id: String, title: String, body: String, cardId: String, cardTitle: String, cardCost: String)
    fun setAllMessages(id: String, cardId: String, chatView: ChatView)
    fun insertMessage(id: String, cardId: String, msg: ChatMessage, chatView: ChatView, cardTitle: String, cardCost: String)
    fun loadReceived(id: String, cardId: String, chatView: ChatView, activity: ChatScreenActivity)
}