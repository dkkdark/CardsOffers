package com.kseniabl.cardtasks.ui.freelancer_details

import com.kseniabl.cardtasks.ui.base.BasePresenter
import javax.inject.Inject

class FreelancerDetailsPresenter<V: FreelancerDetailsView, I: FreelancerDetailsInteractorInterface> @Inject constructor(val interactor: I): FreelancerDetailsPresenterInterface<V>, BasePresenter<V>() {

}