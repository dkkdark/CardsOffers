package com.kseniabl.cardstasks.ui.add_prod

import android.util.Log
import com.kseniabl.cardstasks.db.RepositoryInterface
import com.kseniabl.cardstasks.ui.base.CurrentUserClass
import com.kseniabl.cardstasks.ui.base.RetrofitApiHolder
import com.kseniabl.cardstasks.ui.base.UserCardInteractor
import com.kseniabl.cardstasks.ui.base.UsersCards
import com.kseniabl.cardtasks.ui.add_prod.DraftInteractorInterface
import com.kseniabl.cardstasks.ui.models.CardModel
import com.kseniabl.cardtasks.ui.models.MessageModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.FlowableSubscriber
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.reactivestreams.Subscription
import retrofit2.Retrofit
import javax.inject.Inject

class DraftInteractor @Inject constructor(var retrofit: Retrofit, var currentUserClass: CurrentUserClass, val repository: RepositoryInterface): DraftInteractorInterface, UserCardInteractor() {

    override fun observeCards(recyclerAdapter: DraftAdapter) {
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
                        addCardsToDraftRecycler(card, recyclerAdapter, pos)
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
                val elements = recyclerAdapter.getElements()
                currentUserClass.readSharedPref()?.id?.let { loadCards(it, recyclerAdapter) }
            }

            override fun onError(e: Throwable?) {
                Log.e("qqq", "onError ${e?.message}")
            }

            override fun onComplete() {
            }

        })
    }

    override fun loadCards(id: String, recyclerAdapter: DraftAdapter) {
        loadAddedCards(id).subscribe { data ->
            if (data != null) {
                for (card in data) {
                    addCardsToDraftRecycler(card, recyclerAdapter, 0)

                    if (!UsersCards.getAllCards().contains(card))
                        UsersCards.addCard(card)
                }
            }
        }
    }

    override fun changeCard(id: String, cardId: String, title: String, descr: String, date: String, createTime: Long, cost: Int, active: Boolean, agreement: Boolean) {
        retrofit.create(RetrofitApiHolder::class.java).changeCard(id, cardId, title, descr, date, cost, active, agreement)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MessageModel> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(data: MessageModel?) {
                    if (data?.message == "success") {
                        val card = CardModel(cardId, title, descr, date, createTime, cost, active, agreement, id)
                        UsersCards.changeCard(card)
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.e("qqq", "changeCard error ${e?.message}")
                }

                override fun onComplete() {
                }

            })
    }

    private fun addCardsToDraftRecycler(card: CardModel, draftAdapter: DraftAdapter, pos: Int) {
        val elements = draftAdapter.getElements()
        var addOrNot = true
        for (itm in elements) {
            addOrNot = itm.id != card.id
        }

        if (addOrNot && !elements.contains(card) && !card.active) {
            draftAdapter.addElement(card, pos)
        }
    }

    override fun updateListInServer(list: List<CardModel>) {
        updateList(list)
    }

    override fun deleteCard(cardId: String) {
        repository.deleteAddProdCard(cardId)
    }
}