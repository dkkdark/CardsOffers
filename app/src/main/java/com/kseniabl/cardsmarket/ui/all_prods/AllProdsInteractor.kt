package com.kseniabl.cardsmarket.ui.all_prods

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.kseniabl.cardsmarket.ui.base.RetrofitApiHolder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class AllProdsInteractor @Inject constructor(var retrofit: Retrofit): AllProdsInteractorInterface {

    override fun loadCards(adapter: AllProdsAdapter) {
        retrofit.create(RetrofitApiHolder::class.java).getAllCards()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data ->
                for (cardsList in data) {
                    for (card in cardsList) {
                        val elements = adapter.getElements()
                        var addOrNot = true
                        for (itm in elements) {
                            addOrNot = itm != card
                        }
                        if (addOrNot && !elements.contains(card) && card.active) {
                            adapter.addElement(card)
                        }
                    }
                }
            }
    }
}