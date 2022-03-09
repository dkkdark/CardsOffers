package com.kseniabl.cardsmarket.ui.freelancer_details

import com.kseniabl.cardsmarket.ui.base.BasePresenter
import javax.inject.Inject

class CardsFreelancerPresenter<V: CardsFreelancerView, I: CardsFreelancerInteractorInterface> @Inject constructor(val interactor: I): CardsFreelancerPresenterInterface<V>, BasePresenter<V>() {
}