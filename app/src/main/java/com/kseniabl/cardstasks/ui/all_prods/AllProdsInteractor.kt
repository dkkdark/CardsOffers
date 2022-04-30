package com.kseniabl.cardstasks.ui.all_prods

import android.util.Log
import com.kseniabl.cardstasks.ui.base.AllCardsContainer
import com.kseniabl.cardstasks.ui.base.RetrofitApiHolder
import com.kseniabl.cardstasks.ui.models.CardModel
import com.kseniabl.cardtasks.ui.all_prods.AllProdsInteractorInterface
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class AllProdsInteractor @Inject constructor(var retrofit: Retrofit): AllProdsInteractorInterface {

    override fun loadCards(adapter: AllProdsAdapter) {
        /*retrofit.create(RetrofitApiHolder::class.java).getAllCards()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<List<CardModel>>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(data: List<List<CardModel>>) {
                    for (cardsList in data) {
                        for (card in cardsList) {
                            val elements = adapter.getElements()
                            var addOrNot = true
                            for (itm in elements) {
                                addOrNot = itm != card
                            }
                            if (addOrNot && !elements.contains(card) && card.active) {
                                adapter.addElement(card, 0)
                            }
                        }
                    }
                    val allCards = adapter.getElements()
                    AllCardsContainer.setCards(allCards)
                }

                override fun onError(e: Throwable) {
                    Log.e("qqq", "loadCards error ${e.message}")
                    adapter.clearElements()
                    adapter.addElements(AllCardsContainer.getAllCards())
                }

                override fun onComplete() {
                }

            })*/
    }
}