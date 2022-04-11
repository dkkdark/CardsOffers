package com.kseniabl.cardstasks.ui.freelancer_details

import com.kseniabl.cardstasks.ui.freelancer_details.CardsFreelancerAdapter
import com.kseniabl.cardtasks.ui.base.BaseInteractor

interface CardsFreelancerInteractorInterface: BaseInteractor {
    fun loadCards(id: String, adapter: CardsFreelancerAdapter)
}