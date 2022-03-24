package com.kseniabl.cardstasks.ui.chat

import android.util.Log
import co.intentservice.chatui.ChatView
import co.intentservice.chatui.models.ChatMessage
import com.kseniabl.cardstasks.db.MapOfChatModels
import com.kseniabl.cardtasks.ui.base.BasePresenter
import com.kseniabl.cardtasks.ui.chat.ChatScreenView
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import javax.inject.Inject

class ChatScreenPresenter<V: ChatScreenView, I: ChatScreenInteractorInterface> @Inject constructor(var interactor: I): BasePresenter<V>(),
    ChatScreenPresenterInterface<V> {

    override fun sentMessageWithServer(id: String, title: String, body: String) {
        interactor.sendMessage(id, title, body)
    }

    override fun setAllMessages(id: String, chatView: ChatView) {
        interactor.loadMsg().subscribe(object : SingleObserver<MapOfChatModels> {
            override fun onSubscribe(d: Disposable?) {
            }

            override fun onSuccess(mocm: MapOfChatModels) {
                Log.e("qqq", "loadAll chatmodelList = ${mocm.chatModelList}")
                if (mocm.userId == id) {
                    for (el in mocm.chatModelList) {
                        chatView.addMessage(ChatMessage(el.message, el.timestamp, el.type))
                    }
                }
            }

            override fun onError(e: Throwable?) {
                Log.e("qqq", "setAllMessages onError ${e?.message}")
            }

        })
    }

    override fun insertMessage(id: String, msg: ChatMessage, chatView: ChatView) {
        interactor.insertMsg(id, msg)
    }
}