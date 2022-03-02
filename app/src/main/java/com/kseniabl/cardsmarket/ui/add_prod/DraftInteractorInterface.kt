package com.kseniabl.cardsmarket.ui.add_prod

import com.google.firebase.database.DatabaseReference
import com.kseniabl.cardsmarket.ui.base.BaseInteractor

interface DraftInteractorInterface: BaseInteractor {
    fun observeCards(recyclerAdapter: DraftAdapter)
    fun loadCards(id: String, recyclerAdapter: DraftAdapter)
    fun changeCard(id: String, cardId: String, title: String, descr: String, date: String, createTime: Long, cost: Int, active: Boolean, agreement: Boolean)
}