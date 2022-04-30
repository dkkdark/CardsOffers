package com.kseniabl.cardstasks.ui.chat

import co.intentservice.chatui.ChatView
import co.intentservice.chatui.models.ChatMessage
import com.kseniabl.cardstasks.db.db_models.MapOfChatModels
import com.kseniabl.cardtasks.ui.base.BaseInteractor
import io.reactivex.rxjava3.core.Single

interface ChatScreenInteractorInterface: BaseInteractor {
    fun sendMessage(senderId: String, id: String, title: String, body: String, cardId: String, cardTitle: String, cardCost: String)
    fun loadMsg(id: String): Single<MapOfChatModels>
    fun insertMsg(id: String, cardId: String, msg: ChatMessage, cardTitle: String, cardCost: String)
    fun loadReceivedMsg(id: String, cardId: String, chatView: ChatView, activity: ChatScreenActivity)
}