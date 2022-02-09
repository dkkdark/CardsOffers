package com.kseniabl.cardsmarket.ui.all_prods

import com.kseniabl.cardsmarket.ui.base.BasePresenter
import javax.inject.Inject

class AllOffersPresenter<V: AllOffersView> @Inject constructor(): BasePresenter<V>(), AllOffersPresenterInterface<V> {
}