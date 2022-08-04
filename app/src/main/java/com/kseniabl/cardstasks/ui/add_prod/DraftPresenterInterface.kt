package com.kseniabl.cardtasks.ui.add_prod

import com.kseniabl.cardstasks.ui.add_prod.DraftAdapter
import com.kseniabl.cardstasks.ui.base.AdapterFunctionsCardModelInterface
import com.kseniabl.cardstasks.ui.models.CardModel
import com.kseniabl.cardtasks.ui.base.PresenterInterface

interface DraftPresenterInterface<V: DraftView>: PresenterInterface<V>,
    AdapterFunctionsCardModelInterface {
    fun loadUserCards(id: String, recyclerAdapter: DraftAdapter)
    fun observeDataChange(recyclerAdapter: DraftAdapter)
    fun changeUserCard(id: String, cardId: String, title: String, descr: String, date: String, currentTime: Long, cost: Int, active: Boolean, agreement: Boolean)
    fun listToServer(list: List<CardModel>)

    fun deleteCard(cardId: String)
}