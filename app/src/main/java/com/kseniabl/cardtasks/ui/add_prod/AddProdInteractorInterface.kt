package com.kseniabl.cardtasks.ui.add_prod

import com.kseniabl.cardtasks.ui.base.BaseInteractor

interface AddProdInteractorInterface: BaseInteractor {
    fun observeCards(recyclerAdapter: AddProdsAdapter)
    fun loadCards(id: String, recyclerAdapter: AddProdsAdapter)
    fun changeCard(id: String, cardId: String, title: String, descr: String, date: String, createTime: Long,
                   cost: Int, active: Boolean, agreement: Boolean)
}