package com.kseniabl.cardsmarket.ui.main

import com.kseniabl.cardsmarket.ui.base.BasePresenter
import javax.inject.Inject

class MainPresenter<V: MainView> @Inject constructor(): BasePresenter<V>(), MainPresenterInterface<V> {
}