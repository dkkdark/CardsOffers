package com.kseniabl.cardstasks.ui.chat

import co.intentservice.chatui.models.ChatMessage
import com.kseniabl.cardstasks.db.ChatModel
import com.kseniabl.cardstasks.db.MapOfChatModels
import com.kseniabl.cardtasks.ui.base.BaseInteractor
import com.kseniabl.cardtasks.ui.base.PresenterInterface
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ChatScreenInteractorInterface: BaseInteractor {
    fun sendMessage(id: String, title: String, body: String)
    fun loadMsg(): Single<MapOfChatModels>
    fun insertMsg(id: String, msg: ChatMessage)
}