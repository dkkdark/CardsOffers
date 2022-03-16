package com.kseniabl.cardtasks.ui.chat

import android.util.Log
import com.kseniabl.cardtasks.ui.base.RetrofitApiHolder
import com.kseniabl.cardtasks.ui.base.UserCardInteractor
import com.kseniabl.cardtasks.ui.login.LoginInteractorInterface
import com.kseniabl.cardtasks.ui.models.MessageModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class ChatScreenInteractor @Inject constructor(val retrofit: Retrofit): ChatScreenInteractorInterface {

    override fun sendMessage(id: String, title: String, body: String) {
        retrofit.create(RetrofitApiHolder::class.java).sendMessage(id, title, body)
            .subscribeOn(Schedulers.io())
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

}