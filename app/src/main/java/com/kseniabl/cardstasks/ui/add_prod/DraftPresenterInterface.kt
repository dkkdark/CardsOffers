package com.kseniabl.cardtasks.ui.add_prod

import com.kseniabl.cardstasks.ui.base.AdapterFunctionsCardModelInterface
import com.kseniabl.cardtasks.ui.base.PresenterInterface

interface DraftPresenterInterface<V: DraftView>: PresenterInterface<V>,
    AdapterFunctionsCardModelInterface {
    fun loadUserCards(id: String, recyclerAdapter: DraftAdapter)
    fun observeDataChange(recyclerAdapter: DraftAdapter)
    fun changeUserCard(id: String, cardId: String, title: String, descr: String, date: String, currentTime: Long, cost: Int, active: Boolean, agreement: Boolean)
}