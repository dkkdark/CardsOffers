package com.kseniabl.cardstasks.ui.add_prod

import com.kseniabl.cardstasks.ui.base.AdapterFunctionsCardModelInterface
import com.kseniabl.cardstasks.ui.models.CardModel
import com.kseniabl.cardtasks.ui.add_prod.AddProdsAdapter
import com.kseniabl.cardtasks.ui.base.PresenterInterface

interface AddProdPresenterCardModelInterface<V: AddProdView>: PresenterInterface<V>,
    AdapterFunctionsCardModelInterface {
    fun loadUserCards(id: String, recyclerAdapter: AddProdsAdapter)
    fun listToDB(list: List<CardModel>)
    fun changeUserCard(id: String, cardId: String, title: String, descr: String, date: String, currentTime: Long, cost: Int, active: Boolean, agreement: Boolean)
    fun observeDataChange(recyclerAdapter: AddProdsAdapter)
    fun deleteCard(userId: String, cardId: String)
}