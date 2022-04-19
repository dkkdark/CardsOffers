package com.kseniabl.cardstasks.ui.base

import android.util.Log
import com.kseniabl.cardstasks.ui.models.CardModel
import com.kseniabl.cardtasks.ui.models.MessageModel
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class UserCardInteractor: UserCardInteractorInterface {

    override fun observeChangeCards() =
        UsersCards.createChangeObservableModelChange()

    override fun observeAddCards() =
        UsersCards.createAddObservableModelChange()

    override fun observeDeleteCards() =
        UsersCards.createDeleteObservableModelChange()

    override fun loadAddedCards(id: String): Observable<List<CardModel>> {
        val retrofit = createRetrofit()
        val observable = retrofit.create(RetrofitApiHolder::class.java).getUsersCards(id)
            .retry(3)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        return observable
    }

    override fun replaceToken(oldToken: String, newToken: String, id: String) {
        val retrofit = createRetrofit()
        retrofit.create(RetrofitApiHolder::class.java).replaceToken(id, oldToken, newToken)
            .subscribeOn(Schedulers.io())
            .retry(3)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<MessageModel> {
                override fun onSubscribe(s: Subscription?) {
                }

                override fun onNext(data: MessageModel) {
                    if (data.message == "success") {
                        Log.e("qqq", "token was replaced")
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.e("qqq", "replaceToken onError ${e?.message}")
                }

                override fun onComplete() {
                }
            })
    }

    override fun sendTokenToServer(token: String, id: String) {
        val retrofit = createRetrofit()
        retrofit.create(RetrofitApiHolder::class.java).setToken(id, token)
            .subscribeOn(Schedulers.io())
            .retry(3)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MessageModel> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(data: MessageModel) {
                    if (data.message == "success") {
                        Log.e("qqq", "token setup succeed")
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.e("qqq", "onNewToken onError ${e?.message}")
                }

                override fun onComplete() {
                }
            })
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl("http:///192.168.1.64/")
            .build()
    }


}