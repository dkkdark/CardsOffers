package com.kseniabl.cardtasks.ui.add_prod

import com.kseniabl.cardstasks.ui.add_prod.DraftAdapter
import com.kseniabl.cardstasks.ui.models.CardModel
import com.kseniabl.cardtasks.ui.base.BaseInteractor

interface DraftInteractorInterface: BaseInteractor {
    fun observeCards(recyclerAdapter: DraftAdapter)
    fun loadCards(id: String, recyclerAdapter: DraftAdapter)
    fun changeCard(id: String, cardId: String, title: String, descr: String, date: String, createTime: Long, cost: Int, active: Boolean, agreement: Boolean)

    fun updateListInServer(list: List<CardModel>)
    fun deleteCard(cardId: String)
}