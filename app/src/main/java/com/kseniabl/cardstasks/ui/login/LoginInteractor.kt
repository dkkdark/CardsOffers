package com.kseniabl.cardtasks.ui.login

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.JsonObject
import com.kseniabl.cardstasks.ui.base.CurrentUser
import com.kseniabl.cardtasks.ui.base.RetrofitApiHolder
import com.kseniabl.cardtasks.ui.base.UserCardInteractor
import com.kseniabl.cardstasks.ui.base.UsersCards
import com.kseniabl.cardtasks.ui.models.MessageModel
import com.kseniabl.cardtasks.utils.CardTasksUtils.generateRandomKey
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class LoginInteractor @Inject constructor(val retrofit: Retrofit): LoginInteractorInterface, UserCardInteractor() {

    override fun registrationApiCall(name: String, email: String, password: String): Observable<JsonObject> {
        val userId = generateRandomKey()
        val observable = retrofit.create(RetrofitApiHolder::class.java).createUser(userId, name, email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        return observable
    }

    override fun loginApiCall(email: String, password: String): Observable<JsonObject> {
        val observable = retrofit.create(RetrofitApiHolder::class.java).checkLogin(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        return observable
    }

    override fun loadUserCards(id: String) {
        loadAddedCards(id).subscribe { data ->
            UsersCards.setCards(data)
        }
    }

    override fun setTokenServer() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("qqq", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            Log.e("qqq", "token = $token")
            sendTokenToServer(token)
        })
    }

    private fun sendTokenToServer(token: String) {
        if (CurrentUser.getUser()?.id != null) {
            val id = CurrentUser.getUser()!!.id
            retrofit.create(RetrofitApiHolder::class.java).setToken(id, token)
                .subscribeOn(Schedulers.io())
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
    }
}