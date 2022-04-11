package com.kseniabl.cardstasks.ui.freelancer_details

import android.util.Log
import com.kseniabl.cardstasks.ui.base.RetrofitApiHolder
import com.kseniabl.cardstasks.ui.models.CardModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class CardsFreelancerInteractor @Inject constructor(val retrofit: Retrofit):
    CardsFreelancerInteractorInterface {

    override fun loadCards(id: String, adapter: CardsFreelancerAdapter) {
        retrofit.create(RetrofitApiHolder::class.java).getUsersCards(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<CardModel>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(data: List<CardModel>) {
                    for (card in data) {
                        val elements = adapter.getElements()
                        if (!elements.contains(card) && card.active) {
                            adapter.addElement(card, 0)
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    Log.e("qqq", "CardsFreelancerInteractor onError ${e.message}")
                }

                override fun onComplete() {
                }

            })
    }
}