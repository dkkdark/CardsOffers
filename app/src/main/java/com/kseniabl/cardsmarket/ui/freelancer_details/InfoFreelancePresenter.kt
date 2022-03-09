package com.kseniabl.cardsmarket.ui.freelancer_details

import com.kseniabl.cardsmarket.ui.base.BasePresenter
import javax.inject.Inject

class InfoFreelancePresenter<V: InfoFreelanceView, I: InfoFreelanceInteractorInterface> @Inject constructor(val interactor: I): InfoFreelancePresenterInterface<V>, BasePresenter<V>() {
}