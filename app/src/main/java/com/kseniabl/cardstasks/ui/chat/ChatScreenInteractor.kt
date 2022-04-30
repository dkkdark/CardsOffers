package com.kseniabl.cardstasks.ui.chat

import android.content.Context
import android.util.Log
import co.intentservice.chatui.ChatView
import co.intentservice.chatui.models.ChatMessage
import com.kseniabl.cardstasks.db.db_models.ChatModel
import com.kseniabl.cardstasks.db.db_models.MapOfChatModels
import com.kseniabl.cardstasks.ui.base.*
import com.kseniabl.cardtasks.ui.models.MessageModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.FlowableSubscriber
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.reactivestreams.Subscription
import retrofit2.Retrofit
import javax.inject.Inject

class ChatScreenInteractor @Inject constructor(val retrofit: Retrofit, var context: Context,
                                               val messagesSaveAndLoad: MessageSaveAndLoadInterface,
                                               val chatListSaving: ChatListSavingInterface, val currentUserClass: CurrentUserClassInterface):
    ChatScreenInteractorInterface {

    private var lastAdded: ChatModel? = null

    override fun sendMessage(senderId: String, id: String, title: String, body: String, cardId: String, cardTitle: String, cardCost: String) {
        retrofit.create(RetrofitApiHolder::class.java).sendMessage(senderId, id, title, body, cardId, cardTitle, cardCost)
            .subscribeOn(Schedulers.io())
            .retry(4)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MessageModel> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(data: MessageModel) {
                    if (data.message == "success") {
                        Log.e("qqq", "message sent successful")
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.e("qqq", "onError sendMessage ${e?.message}")
                }

                override fun onComplete() {
                }

            })
    }

    override fun loadReceivedMsg(id: String, cardId: String, chatView: ChatView, activity: ChatScreenActivity) {
        GlobalScope.launch {
            val alreadyAdded = messagesSaveAndLoad.loadAllById(id)
            var isExist = false
            if (alreadyAdded?.cardChatModelList?.isNullOrEmpty() == false) {
                for (card in alreadyAdded.cardChatModelList) {
                    if (card.cardId == cardId) {
                        isExist = true
                        lastAdded = card.chatList.last()
                        setObserver(id, cardId, chatView)
                    }
                }
            }
            if (!isExist) {
                val newList = mutableListOf(CardChatModel(cardId, mutableListOf()))
                messagesSaveAndLoad.setList(id, newList).subscribe(object : SingleObserver<Int> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onSuccess(rows: Int) {
                        setObserver(id, cardId, chatView)
                    }

                    override fun onError(e: Throwable) {
                        Log.e("qqq", "setList onError ${e.message}")
                    }
                })
            }
        }
    }

    private fun setObserver(id: String, cardId: String, chatView: ChatView) {
        messagesSaveAndLoad.getReceivedMsg(id).subscribe(object : FlowableSubscriber<MapOfChatModels> {
            override fun onSubscribe(s: Subscription) {
                s.request(Long.MAX_VALUE)
            }

            override fun onNext(mocm: MapOfChatModels) {
                for (card in mocm.cardChatModelList) {
                    if (card.cardId == cardId) {
                        Log.e("qqq", "------ load received ------")
                        if (card.chatList.size == 0)
                            return
                        val el = card.chatList.last()
                        Log.e("qqq", "${el.type}     ${el}    $lastAdded")
                        if (el.type == ChatMessage.Type.RECEIVED && el != lastAdded) {
                            chatView.addMessage(ChatMessage(el.message, el.timestamp, el.type))
                            lastAdded = el
                        }
                    }
                }
            }

            override fun onError(t: Throwable?) {
                Log.e("qqq", "setObserver onError ${t?.message}")
            }

            override fun onComplete() {
            }

        })
    }

    override fun loadMsg(id: String): Single<MapOfChatModels> {
        //messagesSaveAndLoad.dellAll()
        return messagesSaveAndLoad.getAllData(id)
    }

    override fun insertMsg(id: String, cardId: String, msg: ChatMessage, cardTitle: String, cardCost: String) {
        messagesSaveAndLoad.setNewList(id, cardId, ChatModel(message = msg.message, timestamp = msg.timestamp, type = msg.type))

        val list = arrayListOf<ChatWithModel>()
        chatListSaving.getChatList()?.let { list.addAll(it) }
        val listToRemove = arrayListOf<ChatWithModel>()
        var newEl: ChatWithModel? = null

        currentUserClass.readSharedPref()?.let {
            for (el in list) {
                if (el.id == id && el.card_id == cardId) {
                    listToRemove.add(el)
                    newEl = ChatWithModel(el.id, el.username, msg.message, 0, el.card_id, el.card_title, el.card_cost)
                }
            }
            list.removeAll(listToRemove)
            if (newEl != null)
                list.add(newEl!!)
            else {
                list.add(ChatWithModel(id, currentUserClass.readSharedPref()!!.username, msg.message, 0, cardId, cardTitle, cardCost))
            }
            chatListSaving.saveChatList(list)
            notificationHandler.notificationHandle()
        }
    }

    companion object {
        var notificationHandler = NotificationHandler()
    }
}