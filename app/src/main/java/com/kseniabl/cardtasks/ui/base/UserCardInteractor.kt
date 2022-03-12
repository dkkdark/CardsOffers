package com.kseniabl.cardtasks.ui.base

import com.kseniabl.cardtasks.ui.models.CardModel
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class UserCardInteractor: UserCardInteractorInterface {

    override fun observeChangeCards() =
        UsersCards.createChangeObservableModelChange()

    override fun observeAddCards() =
        UsersCards.createAddObservableModelChange()

    override fun loadAddedCards(id: String): Observable<List<CardModel>> {
        val retrofit = createRetrofit()
        val observable = retrofit.create(RetrofitApiHolder::class.java).getUsersCards(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        return observable
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl("http://192.168.1.64:80/")
            .build()
    }


}