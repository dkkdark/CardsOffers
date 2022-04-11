package com.kseniabl.cardstasks.ui.freelancer_details

import com.kseniabl.cardstasks.ui.base.BasePresenter
import com.kseniabl.cardtasks.ui.freelancer_details.FreelancerDetailsInteractorInterface
import com.kseniabl.cardtasks.ui.freelancer_details.FreelancerDetailsPresenterInterface
import javax.inject.Inject

class FreelancerDetailsPresenter<V: FreelancerDetailsView, I: FreelancerDetailsInteractorInterface> @Inject constructor(val interactor: I): FreelancerDetailsPresenterInterface<V>, BasePresenter<V>() {

}