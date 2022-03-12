package com.kseniabl.cardtasks.ui.login

import com.google.gson.JsonObject
import com.kseniabl.cardtasks.ui.base.RetrofitApiHolder
import com.kseniabl.cardtasks.ui.base.UserCardInteractor
import com.kseniabl.cardtasks.ui.base.UsersCards
import com.kseniabl.cardtasks.utils.CardTasksUtils.generateRandomKey
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
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
}