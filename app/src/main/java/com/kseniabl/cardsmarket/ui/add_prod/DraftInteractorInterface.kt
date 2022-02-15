package com.kseniabl.cardsmarket.ui.add_prod

import com.google.firebase.database.DatabaseReference
import com.kseniabl.cardsmarket.ui.base.BaseInteractor

interface DraftInteractorInterface: BaseInteractor {
    fun getChilds(): DatabaseReference?
}