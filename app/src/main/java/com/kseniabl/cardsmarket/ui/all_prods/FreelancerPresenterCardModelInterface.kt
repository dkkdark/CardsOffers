package com.kseniabl.cardsmarket.ui.all_prods

import com.kseniabl.cardsmarket.ui.base.AdapterFunctionsCardModelInterface
import com.kseniabl.cardsmarket.ui.base.PresenterInterface

interface FreelancerPresenterCardModelInterface<V: FreelancerView>: PresenterInterface<V>, AdapterFunctionsFreelancerModelInterface {
    fun loadFreelancers()
}