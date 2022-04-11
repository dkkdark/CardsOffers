package com.kseniabl.cardtasks.ui.all_prods

import com.kseniabl.cardstasks.ui.base.BasePresenter
import javax.inject.Inject

class AllOffersPresenter<V: AllOffersView> @Inject constructor(): BasePresenter<V>(), AllOffersPresenterInterface<V> {
}