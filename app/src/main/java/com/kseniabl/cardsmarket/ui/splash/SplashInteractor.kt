package com.kseniabl.cardsmarket.ui.splash

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.kseniabl.cardsmarket.ui.base.CurrentUser
import com.kseniabl.cardsmarket.ui.base.RetrofitApiHolder
import com.kseniabl.cardsmarket.ui.base.UserCardInteractor
import com.kseniabl.cardsmarket.ui.base.UsersCards
import com.kseniabl.cardsmarket.ui.models.BaseProfileInfoModel
import com.kseniabl.cardsmarket.ui.models.UserModel
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class SplashInteractor @Inject constructor(var context: Context): SplashInteractorInterface, UserCardInteractor() {

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl("http://10.0.2.2:5000/")
            .build()
    }

    override fun isUserLogin(token: String) {
        Log.e("qqq", "$token")
        val retrofit = createRetrofit()
        retrofit.create(RetrofitApiHolder::class.java).isCurrentUserExist("Bearer $token")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<UserModel> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(data: UserModel?) {
                    if (data != null) {
                        CurrentUser.setUser(data)
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
        loadAddedCards(id).subscribe { data ->
            UsersCards.setCards(data)
            Log.e("qqq", "users cards was set $data")
        }
    }
}