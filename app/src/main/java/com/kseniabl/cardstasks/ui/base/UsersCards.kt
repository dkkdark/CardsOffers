package com.kseniabl.cardstasks.ui.base

import android.os.Message
import com.kseniabl.cardstasks.ui.models.CardModel
import com.kseniabl.cardtasks.ui.models.MessageModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.processors.PublishProcessor
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlin.collections.ArrayList


object UsersCards {
    private val cards = arrayListOf<CardModel>()
    private val changeObservable: PublishProcessor<CardModel> = PublishProcessor.create()
    private val addObservable: PublishProcessor<CardModel> = PublishProcessor.create()
    private val deleteObservable: PublishProcessor<CardModel> = PublishProcessor.create()

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

    fun deleteCard(cardId: String) {
        for (card in cards) {
            if (card.id == cardId) {
                cards.remove(card)
                deleteObservable.onNext(card)
            }
        }
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

    fun createDeleteObservableModelChange(): PublishProcessor<CardModel> {
        return deleteObservable
    }
}