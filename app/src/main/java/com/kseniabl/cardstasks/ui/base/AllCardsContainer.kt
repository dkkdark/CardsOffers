package com.kseniabl.cardstasks.ui.base

import com.kseniabl.cardstasks.ui.models.CardModel

object AllCardsContainer {

    private val cards = arrayListOf<CardModel>()

    fun addCard(card: CardModel) {
        cards.add(card)
    }

    fun setCards(allCards: List<CardModel>) {
        UsersCards.clearCards()
        cards.addAll(allCards)
    }

    fun getAllCards(): ArrayList<CardModel> {
        return cards
    }
}