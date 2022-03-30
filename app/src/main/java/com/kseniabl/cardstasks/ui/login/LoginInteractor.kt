package com.kseniabl.cardstasks.ui.login

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.JsonObject
import com.kseniabl.cardstasks.ui.base.CurrentUserClass
import com.kseniabl.cardstasks.ui.base.RetrofitApiHolder
import com.kseniabl.cardstasks.ui.base.UserCardInteractor
import com.kseniabl.cardstasks.ui.base.UsersCards
import com.kseniabl.cardstasks.utils.CardTasksUtils.generateRandomKey
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class LoginInteractor @Inject constructor(val retrofit: Retrofit, val currentUserClass: CurrentUserClass): LoginInteractorInterface, UserCardInteractor() {

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

    override fun setToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("qqq", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            Log.e("qqq", "token = $token")
            if (currentUserClass.readSharedPref()?.messagingToken.isNullOrEmpty() && currentUserClass.readSharedPref() != null) {
                Log.e("qqq", "first time set token")
                sendTokenToServer(token, currentUserClass.readSharedPref()!!.id)
                saveUserToken(token)
            }
            else if (currentUserClass.readSharedPref()?.messagingToken != token && currentUserClass.readSharedPref() != null) {
                Log.e("qqq", "replace token after login")
                replaceToken(currentUserClass.readSharedPref()!!.messagingToken, token, currentUserClass.readSharedPref()!!.id)
                saveUserToken(token)
            }
        })
    }

    private fun saveUserToken(token: String) {
        val user = currentUserClass.readSharedPref()
        user!!.messagingToken = token
        currentUserClass.saveCurrentUser(user)
    }
}