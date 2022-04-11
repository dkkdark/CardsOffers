package com.kseniabl.cardstasks.ui.base

import com.kseniabl.cardstasks.ui.models.CardModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.processors.PublishProcessor
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlin.collections.ArrayList


object UsersCards {
    private val cards = arrayListOf<CardModel>()
    private val changeObservable: PublishProcessor<CardModel> = PublishProcessor.create()
    private val addObservable: PublishProcessor<CardModel> = PublishProcessor.create()

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

    fun createChangeObservableModelChange(): PublishProcessor<CardModel> {
        return changeObservable
    }

    fun createAddObservableModelChange(): PublishProcessor<CardModel> {
        return addObservable
    }
}