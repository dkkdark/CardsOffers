package com.kseniabl.cardstasks.ui.splash

import android.content.Context
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.kseniabl.cardstasks.ui.base.CurrentUserClass
import com.kseniabl.cardstasks.ui.base.RetrofitApiHolder
import com.kseniabl.cardstasks.ui.base.UserCardInteractor
import com.kseniabl.cardstasks.ui.base.UsersCards
import com.kseniabl.cardtasks.ui.models.CardModel
import com.kseniabl.cardstasks.ui.models.UserModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class SplashInteractor @Inject constructor(var retrofit: Retrofit, var context: Context, var currentUserClass: CurrentUserClass): SplashInteractorInterface, UserCardInteractor() {

    override fun isUserExist(token: String): Flowable<UserModel> {
        return retrofit.create(RetrofitApiHolder::class.java).isCurrentUserExist("Bearer $token")
            .subscribeOn(Schedulers.io())
            .retry(3)
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun changeToken(oldToken: String, newToken: String, id: String) {
        replaceToken(oldToken, newToken, id)
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