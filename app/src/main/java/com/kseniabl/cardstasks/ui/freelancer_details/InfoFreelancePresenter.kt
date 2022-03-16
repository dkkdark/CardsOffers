package com.kseniabl.cardtasks.ui.freelancer_details

import com.kseniabl.cardtasks.ui.base.BasePresenter
import javax.inject.Inject

class InfoFreelancePresenter<V: InfoFreelanceView, I: InfoFreelanceInteractorInterface> @Inject constructor(val interactor: I): InfoFreelancePresenterInterface<V>, BasePresenter<V>() {
}