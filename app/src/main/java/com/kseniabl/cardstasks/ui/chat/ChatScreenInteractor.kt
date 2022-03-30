package com.kseniabl.cardstasks.ui.chat

import android.content.Context
import android.util.Log
import co.intentservice.chatui.ChatView
import co.intentservice.chatui.models.ChatMessage
import com.kseniabl.cardstasks.db.ChatModel
import com.kseniabl.cardstasks.db.MapOfChatModels
import com.kseniabl.cardstasks.ui.base.MessageSaveAndLoadInterface
import com.kseniabl.cardstasks.ui.base.RetrofitApiHolder
import com.kseniabl.cardtasks.ui.models.MessageModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableSubscriber
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subscribers.DisposableSubscriber
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import retrofit2.Retrofit
import javax.inject.Inject

class ChatScreenInteractor @Inject constructor(val retrofit: Retrofit, var context: Context, val messagesSaveAndLoad: MessageSaveAndLoadInterface):
    ChatScreenInteractorInterface {

    private var lastAdded: ChatModel? = null

    override fun sendMessage(senderId: String, id: String, title: String, body: String) {
        retrofit.create(RetrofitApiHolder::class.java).sendMessage(senderId, id, title, body)
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

    override fun loadReceivedMsg(id: String, chatView: ChatView, activity: ChatScreenActivity) {
        GlobalScope.launch {
            val alreadyAdded = messagesSaveAndLoad.loadAllById(id)
            if (alreadyAdded?.chatModelList?.isNullOrEmpty() == false) {
                val addedEl = alreadyAdded.chatModelList.last()
                setObserver(id, chatView, addedEl)
            }
        }
    }

    private fun setObserver(id: String, chatView: ChatView, addedEl: ChatModel) {
        messagesSaveAndLoad.getReceivedMsg(id).subscribe(object : Subscriber<MapOfChatModels> {
            override fun onSubscribe(s: Subscription) {
                s.request(Long.MAX_VALUE)
            }

            override fun onNext(mocm: MapOfChatModels) {
                if (mocm.userId == id) {
                    val el = mocm.chatModelList.last()
                    Log.e("qqq", "------ load received ------")
                    Log.e("qqq", "${el.type}     ${el}    $lastAdded   $addedEl")
                    if (el.type == ChatMessage.Type.RECEIVED && el != addedEl && el != lastAdded) {
                        chatView.addMessage(ChatMessage(el.message, el.timestamp, el.type))
                        lastAdded = el
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

    override fun insertMsg(id: String, msg: ChatMessage) {
        messagesSaveAndLoad.setNewList(id, ChatModel(message = msg.message, timestamp = msg.timestamp, type = msg.type))
    }
}