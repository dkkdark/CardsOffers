package com.kseniabl.cardsmarket.ui.add_prod

import com.kseniabl.cardsmarket.ui.base.AdapterFunctionsCardModelInterface
import com.kseniabl.cardsmarket.ui.base.PresenterInterface

interface AddProdPresenterCardModelInterface<V: AddProdView>: PresenterInterface<V>, AdapterFunctionsCardModelInterface {
    fun loadBaseCards()
    fun loadUserCards(id: String, recyclerAdapter: AddProdsAdapter)
    fun changeUserCard(id: String, cardId: String, title: String, descr: String, date: String, currentTime: Long, cost: Int, active: Boolean, agreement: Boolean)
    fun observeDataChange(recyclerAdapter: AddProdsAdapter)
}