package com.kseniabl.cardsmarket.ui.add_prod

import com.google.firebase.database.DatabaseReference
import com.kseniabl.cardsmarket.ui.base.UserCardInteractor
import javax.inject.Inject

class DraftInteractor @Inject constructor(): DraftInteractorInterface, UserCardInteractor() {

    override fun getChilds(): DatabaseReference? = childChanged()
}