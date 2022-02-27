package com.kseniabl.cardsmarket.ui.base

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.kseniabl.cardsmarket.ui.add_prod.AddProdsAdapter
import com.kseniabl.cardsmarket.ui.add_prod.DraftAdapter
import com.kseniabl.cardsmarket.ui.models.CardModel
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

abstract class UserCardInteractor: UserCardInteractorInterface {

    override fun observeAddCards() =
        UsersCards.createObservableModelChange()

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
            .baseUrl("http://10.0.2.2:5000/")
            .build()
    }


}