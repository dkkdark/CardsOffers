package com.kseniabl.cardstasks.ui.all_prods

import com.kseniabl.cardtasks.ui.base.PresenterInterface

interface FreelancerPresenterCardModelInterface<V: FreelancerView>: PresenterInterface<V>,
    AdapterFunctionsFreelancerModelInterface {
    fun loadFreelancers()
}