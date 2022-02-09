package com.kseniabl.cardsmarket.ui.base

import com.kseniabl.cardsmarket.ui.all_prods.CardModel

object UsersCards {
    private val cards = arrayListOf<CardModel>()

    fun setCards(allCards: ArrayList<CardModel>) {
        clearCards()
        cards.addAll(allCards)
    }

    fun addCard(card: CardModel) {
        cards.add(card)
    }

    fun getAllCards(): ArrayList<CardModel> {
        return cards
    }

    fun clearCards() {
        cards.clear()
    }
}