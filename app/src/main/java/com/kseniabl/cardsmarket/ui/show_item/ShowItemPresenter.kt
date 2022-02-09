package com.kseniabl.cardsmarket.ui.show_item

import com.kseniabl.cardsmarket.ui.base.BasePresenter
import javax.inject.Inject

class ShowItemPresenter<V: ShowItemView, I: ShowItemInteractorInterface> @Inject constructor(): ShowItemPresenterInterface<V>, BasePresenter<V>() {

}