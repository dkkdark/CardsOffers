package com.kseniabl.cardtasks.ui.all_prods

import com.kseniabl.cardtasks.ui.base.AdapterFunctionsCardModelInterface
import com.kseniabl.cardtasks.ui.base.PresenterInterface

interface AllProdsPresenterCardModelInterface<V: AllProdsView>: PresenterInterface<V>, AdapterFunctionsCardModelInterface {
    fun loadAllCards(adapter: AllProdsAdapter)
}