package com.kseniabl.cardtasks.ui.base

import com.kseniabl.cardtasks.ui.models.CardModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlin.collections.ArrayList


object UsersCards {
    private val cards = arrayListOf<CardModel>()
    private val changeObservable: BehaviorSubject<CardModel> = BehaviorSubject.create()
    private val addObservable: BehaviorSubject<CardModel> = BehaviorSubject.create()

    fun setCards(allCards: List<CardModel>) {
        clearCards()
        cards.addAll(allCards)
    }

    fun changeCard(card: CardModel) {
        val filterCard = cards.filter { it.id == card.id }[0]
        cards.remove(filterCard)
        cards.add(card)
        changeObservable.onNext(card)
    }

    fun addCard(card: CardModel) {
        cards.add(card)
        addObservable.onNext(card)
    }

    fun getAllCards(): ArrayList<CardModel> {
        return cards
    }

    fun clearCards() {
        cards.clear()
    }

    fun createChangeObservableModelChange(): Observable<CardModel> {
        return changeObservable
    }

    fun createAddObservableModelChange(): Observable<CardModel> {
        return addObservable
    }
}