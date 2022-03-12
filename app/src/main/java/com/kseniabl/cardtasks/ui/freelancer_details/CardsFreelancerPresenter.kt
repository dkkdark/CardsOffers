package com.kseniabl.cardtasks.ui.freelancer_details

import com.kseniabl.cardtasks.ui.base.BasePresenter
import javax.inject.Inject

class CardsFreelancerPresenter<V: CardsFreelancerView, I: CardsFreelancerInteractorInterface> @Inject constructor(val interactor: I): CardsFreelancerPresenterInterface<V>, BasePresenter<V>() {
}