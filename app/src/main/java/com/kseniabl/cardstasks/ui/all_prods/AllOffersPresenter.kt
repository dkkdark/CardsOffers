package com.kseniabl.cardstasks.ui.all_prods

import com.kseniabl.cardstasks.ui.base.BasePresenter
import com.kseniabl.cardtasks.ui.all_prods.AllOffersPresenterInterface
import com.kseniabl.cardtasks.ui.all_prods.AllOffersView
import javax.inject.Inject

class AllOffersPresenter<V: AllOffersView> @Inject constructor(): BasePresenter<V>(),
    AllOffersPresenterInterface<V> {
}