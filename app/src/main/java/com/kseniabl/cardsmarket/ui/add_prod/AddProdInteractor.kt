package com.kseniabl.cardsmarket.ui.add_prod

import com.google.firebase.database.DatabaseReference
import com.kseniabl.cardsmarket.ui.base.UserCardInteractor
import javax.inject.Inject

class AddProdInteractor @Inject constructor(): AddProdInteractorInterface, UserCardInteractor() {

    override fun getCards() = loadCards()
    override fun getChilds(): DatabaseReference? = childChanged()

}