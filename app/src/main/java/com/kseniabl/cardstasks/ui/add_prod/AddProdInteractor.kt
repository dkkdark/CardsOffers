package com.kseniabl.cardstasks.ui.add_prod

import android.util.Log
import com.kseniabl.cardstasks.ui.base.*
import com.kseniabl.cardstasks.ui.models.CardModel
import com.kseniabl.cardtasks.ui.add_prod.AddProdsAdapter
import com.kseniabl.cardtasks.ui.models.MessageModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableSubscriber
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.reactivestreams.Subscription
import retrofit2.Retrofit
import javax.inject.Inject

class AddProdInteractor @Inject constructor(var retrofit: Retrofit, var currentUserClass: CurrentUserClassInterface): AddProdInteractorInterface, UserCardInteractor() {

    override fun observeCards(recyclerAdapter: AddProdsAdapter) {
        observeChangeCards()
            .subscribe(object : FlowableSubscriber<CardModel> {
                override fun onSubscribe(s: Subscription) {
                    s.request(Long.MAX_VALUE)
                }

                override fun onNext(card: CardModel) {
                    val elements = recyclerAdapter.getElements()
                    try {
                        val cardForDelete = elements.filter { it.id == card.id }[0]
                        val pos = recyclerAdapter.getElementPos(cardForDelete)
                        recyclerAdapter.deleteElement(cardForDelete)
                        addCardsToAddProdsRecycler(card, recyclerAdapter, pos)
                    } catch (e: IndexOutOfBoundsException) {
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.e("qqq", "onError ${e?.message}")
                }

                override fun onComplete() {
                }

            })

        observeAddCards().subscribe(object : FlowableSubscriber<CardModel> {
            override fun onSubscribe(s: Subscription) {
                s.request(Long.MAX_VALUE)
            }

            override fun onNext(card: CardModel) {
                if (currentUserClass.readSharedPref()?.id != null)
                    loadCards(currentUserClass.readSharedPref()!!.id, recyclerAdapter)
            }

            override fun onError(e: Throwable?) {
                Log.e("qqq", "onError ${e?.message}")
            }

            override fun onComplete() {
            }

        })

        observeDeleteCards().subscribe(object : FlowableSubscriber<CardModel> {
            override fun onSubscribe(s: Subscription) {
                s.request(Long.MAX_VALUE)
            }

            override fun onNext(card: CardModel) {
                recyclerAdapter.deleteElement(card)
            }

            override fun onError(t: Throwable) {
                Log.e("qqq", "observeDeleteCards onError ${t.message}")
            }

            override fun onComplete() {
            }

        })

    }


    override fun loadCards(id: String, recyclerAdapter: AddProdsAdapter) {
        loadAddedCards(id).subscribe { data ->
            Log.e("qqq", "loadAdded $data")
            for (card in data) {
                    addCardsToAddProdsRecycler(card, recyclerAdapter, 0)

                if (!UsersCards.getAllCards().contains(card))
                    UsersCards.addCard(card)
            }
        }
    }

    override fun changeCard(id: String, cardId: String, title: String, descr: String, date: String, createTime: Long, cost: Int, active: Boolean, agreement: Boolean) {
        retrofit.create(RetrofitApiHolder::class.java).changeCard(id, cardId, title, descr, date, cost, active, agreement)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MessageModel> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(data: MessageModel) {
                    if (data.message == "success") {
                        val card = CardModel(cardId, title, descr, date, createTime, cost, active, agreement, id)
                        UsersCards.changeCard(card)
                    }
                }

                override fun onError(e: Throwable) {
                    Log.e("qqq", "changeCard error ${e.message}")
                }

                override fun onComplete() {
                }

            })
    }

    override fun deleteCard(userId: String, cardId: String): Flowable<MessageModel> {
        return retrofit.create(RetrofitApiHolder::class.java).deleteCard(userId, cardId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun addCardsToAddProdsRecycler(card: CardModel, addProdsAdapter: AddProdsAdapter, pos: Int) {
        val elements = addProdsAdapter.getElements()
        var addOrNot = true
        for (itm in elements) {
            addOrNot = itm.id != card.id
        }

        if (addOrNot && !elements.contains(card) && card.active) {
            addProdsAdapter.addElement(card, pos)
        }
    }
}