package com.kseniabl.cardsmarket.ui.freelancer_details

import com.kseniabl.cardsmarket.ui.base.BasePresenter
import javax.inject.Inject

class FreelancerDetailsPresenter<V: FreelancerDetailsView, I: FreelancerDetailsInteractorInterface> @Inject constructor(val interactor: I): FreelancerDetailsPresenterInterface<V>, BasePresenter<V>() {

}