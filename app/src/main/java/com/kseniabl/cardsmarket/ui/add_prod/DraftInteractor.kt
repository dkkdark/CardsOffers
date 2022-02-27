package com.kseniabl.cardsmarket.ui.add_prod

import com.google.firebase.database.DatabaseReference
import com.kseniabl.cardsmarket.ui.base.CurrentUser
import com.kseniabl.cardsmarket.ui.base.UserCardInteractor
import com.kseniabl.cardsmarket.ui.base.UsersCards
import com.kseniabl.cardsmarket.ui.models.CardModel
import javax.inject.Inject

class DraftInteractor @Inject constructor(): DraftInteractorInterface, UserCardInteractor() {

    override fun observeCards(recyclerAdapter: DraftAdapter) {
        observeAddCards().subscribe {
            if (CurrentUser.getUser()?.id != null) {
                loadCards(CurrentUser.getUser()!!.id, recyclerAdapter)
            }
        }
    }

    override fun loadCards(id: String, recyclerAdapter: DraftAdapter) {
        loadAddedCards(id).subscribe { data ->
            if (data != null) {
                for (card in data) {
                    addCardsToDraftRecycler(card, recyclerAdapter)

                    if (!UsersCards.getAllCards().contains(card))
                        UsersCards.addCard(card)
                }
            }
        }
    }

    private fun addCardsToDraftRecycler(card: CardModel, draftAdapter: DraftAdapter) {
        val elements = draftAdapter.getElements()
        var addOrNot = true
        for (itm in elements) {
            addOrNot = itm.id != card.id
        }

        if (addOrNot && !elements.contains(card) && !card.active) {
            draftAdapter.addElement(card)
        }
    }

}