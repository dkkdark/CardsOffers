package com.kseniabl.cardstasks.ui.freelancer_details

import com.kseniabl.cardstasks.ui.base.AdapterFunctionsCardModelInterface
import com.kseniabl.cardtasks.ui.base.PresenterInterface

interface CardsFreelancerPresenterInterface<V: CardsFreelancerView>: PresenterInterface<V>,
    AdapterFunctionsCardModelInterface {
    fun loadCardsToRecycler(id: String, adapter: CardsFreelancerAdapter)
}