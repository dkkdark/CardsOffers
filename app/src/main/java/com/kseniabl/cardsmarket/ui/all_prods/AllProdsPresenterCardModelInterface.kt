package com.kseniabl.cardsmarket.ui.all_prods

import com.kseniabl.cardsmarket.ui.base.AdapterFunctionsCardModelInterface
import com.kseniabl.cardsmarket.ui.base.PresenterInterface

interface AllProdsPresenterCardModelInterface<V: AllProdsView>: PresenterInterface<V>, AdapterFunctionsCardModelInterface {
    fun loadAllCards(adapter: AllProdsAdapter)
}