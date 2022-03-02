package com.kseniabl.cardsmarket.ui.add_prod

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.kseniabl.cardsmarket.ui.base.BaseInteractor

interface AddProdInteractorInterface: BaseInteractor {
    fun observeCards(recyclerAdapter: AddProdsAdapter)
    fun loadCards(id: String, recyclerAdapter: AddProdsAdapter)
    fun changeCard(id: String, cardId: String, title: String, descr: String, date: String, createTime: Long, cost: Int, active: Boolean, agreement: Boolean)
}