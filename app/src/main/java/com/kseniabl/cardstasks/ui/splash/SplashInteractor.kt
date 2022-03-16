package com.kseniabl.cardtasks.ui.splash

import android.content.Context
import android.util.Log
import com.kseniabl.cardstasks.ui.base.CurrentUser
import com.kseniabl.cardtasks.ui.base.RetrofitApiHolder
import com.kseniabl.cardtasks.ui.base.UserCardInteractor
import com.kseniabl.cardstasks.ui.base.UsersCards
import com.kseniabl.cardtasks.ui.models.CardModel
import com.kseniabl.cardstasks.ui.models.UserModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class SplashInteractor @Inject constructor(var retrofit: Retrofit, var context: Context): SplashInteractorInterface, UserCardInteractor() {

    override fun isUserLogin(token: String) {
        retrofit.create(RetrofitApiHolder::class.java).isCurrentUserExist("Bearer $token")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<UserModel> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(data: UserModel?) {
                    if (data != null) {
                        CurrentUser.setUser(data)
                        CurrentUser.setToken(token)
                        if (CurrentUser.getUser()?.id != null)
                            loadUserCards(CurrentUser.getUser()!!.id)
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.e("qqq", "splash onError ${e?.message}")
                }

                override fun onComplete() {
                }
            })
    }

    override fun loadUserCards(id: String) {
        loadAddedCards(id).subscribe(object : Observer<List<CardModel>> {
            override fun onSubscribe(d: Disposable?) {
            }

            override fun onNext(data: List<CardModel>) {
                UsersCards.setCards(data)
            }

            override fun onError(e: Throwable?) {
                Log.e("qqq", "loadUserCards error ${e?.message}")
            }

            override fun onComplete() {
            }

        })
    }
}