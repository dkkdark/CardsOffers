package com.kseniabl.cardstasks.ui.chat

import android.content.Context
import android.util.Log
import co.intentservice.chatui.models.ChatMessage
import com.kseniabl.cardstasks.db.ChatModel
import com.kseniabl.cardstasks.db.MapOfChatModels
import com.kseniabl.cardstasks.ui.base.MessageSaveAndLoadInterface
import com.kseniabl.cardtasks.ui.base.RetrofitApiHolder
import com.kseniabl.cardtasks.ui.models.MessageModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class ChatScreenInteractor @Inject constructor(val retrofit: Retrofit, var context: Context, val messagesSaveAndLoad: MessageSaveAndLoadInterface):
    ChatScreenInteractorInterface {

    override fun sendMessage(id: String, title: String, body: String) {
        retrofit.create(RetrofitApiHolder::class.java).sendMessage(id, title, body)
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

    override fun loadMsg(): Single<MapOfChatModels> {
        //messagesSaveAndLoad.dellAll()
        return messagesSaveAndLoad.getAllData()
    }

    override fun insertMsg(id: String, msg: ChatMessage) {
        messagesSaveAndLoad.setNewList(id, ChatModel(message = msg.message, timestamp = msg.timestamp, type = msg.type))
    }
}