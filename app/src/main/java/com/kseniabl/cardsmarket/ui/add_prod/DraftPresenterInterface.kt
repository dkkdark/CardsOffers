package com.kseniabl.cardsmarket.ui.add_prod

import com.kseniabl.cardsmarket.ui.base.AdapterFunctionsCardModelInterface
import com.kseniabl.cardsmarket.ui.base.PresenterInterface

interface DraftPresenterInterface<V: DraftView>: PresenterInterface<V>, AdapterFunctionsCardModelInterface {
    fun loadUserCards(id: String, recyclerAdapter: DraftAdapter)
}