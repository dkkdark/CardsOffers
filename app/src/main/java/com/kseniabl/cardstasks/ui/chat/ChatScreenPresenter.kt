package com.kseniabl.cardstasks.ui.chat

import android.util.Log
import co.intentservice.chatui.ChatView
import co.intentservice.chatui.models.ChatMessage
import com.kseniabl.cardstasks.db.db_models.MapOfChatModels
import com.kseniabl.cardstasks.ui.base.BasePresenter
import com.kseniabl.cardtasks.ui.chat.ChatScreenView
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class ChatScreenPresenter<V: ChatScreenView, I: ChatScreenInteractorInterface> @Inject constructor(var interactor: I): BasePresenter<V>(),
    ChatScreenPresenterInterface<V> {

    override fun sentMessageWithServer(senderId: String, id: String, title: String, body: String, cardId: String, cardTitle: String, cardCost: String) {
        interactor.sendMessage(senderId, id, title, body, cardId, cardTitle, cardCost)
    }

    override fun setAllMessages(id: String, cardId: String, chatView: ChatView) {
        interactor.loadMsg(id).subscribe(object : SingleObserver<MapOfChatModels> {
            override fun onSubscribe(d: Disposable?) {
            }

            override fun onSuccess(mocm: MapOfChatModels) {
                Log.e("qqq", "update messages")
                for (card in mocm.cardChatModelList) {
                    if (card.cardId == cardId) {
                        for (el in card.chatList) {
                            chatView.addMessage(ChatMessage(el.message, el.timestamp, el.type))
                        }
                    }
                }
            }

            override fun onError(e: Throwable?) {
                Log.e("qqq", "setAllMessages onError ${e?.message}")
            }

        })
    }

    override fun loadReceived(id: String, cardId: String, chatView: ChatView, activity: ChatScreenActivity) {
        interactor.loadReceivedMsg(id, cardId, chatView, activity)
    }

    override fun insertMessage(id: String, cardId: String, msg: ChatMessage, chatView: ChatView, cardTitle: String, cardCost: String) {
        interactor.insertMsg(id, cardId, msg, cardTitle, cardCost)
    }
}