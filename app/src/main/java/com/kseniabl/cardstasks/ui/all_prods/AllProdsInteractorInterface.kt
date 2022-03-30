package com.kseniabl.cardtasks.ui.all_prods

import com.kseniabl.cardstasks.ui.all_prods.AllProdsAdapter
import com.kseniabl.cardtasks.ui.base.BaseInteractor

interface AllProdsInteractorInterface: BaseInteractor {
    fun loadCards(adapter: AllProdsAdapter)
}