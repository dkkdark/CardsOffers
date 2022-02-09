package com.kseniabl.cardsmarket.ui.add_prod

import com.kseniabl.cardsmarket.ui.base.BasePresenter
import javax.inject.Inject

class DraftPresenter<V: DraftView> @Inject constructor(): BasePresenter<V>(), DraftPresenterInterface<V> {
}