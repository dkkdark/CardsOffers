package com.kseniabl.cardsmarket.ui.base

import com.kseniabl.cardsmarket.ui.models.CardModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlin.collections.ArrayList


object UsersCards {
    private val cards = arrayListOf<CardModel>()
    private val changeObservable: PublishSubject<UsersCards> = PublishSubject.create()

    fun setCards(allCards: List<CardModel>) {
        clearCards()
        cards.addAll(allCards)
    }

    fun addCard(card: CardModel) {
        cards.add(card)
        changeObservable.onNext(this)
    }

    fun getAllCards(): ArrayList<CardModel> {
        return cards
    }

    fun clearCards() {
        cards.clear()
    }

    fun getLastId(): Int {
        return cards.size
    }

    fun createObservableModelChange(): Observable<UsersCards> {
        return changeObservable
    }
}