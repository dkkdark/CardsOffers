package com.kseniabl.cardsmarket.ui.all_prods

import com.kseniabl.cardsmarket.ui.base.BaseView

interface FreelancerView: BaseView {
    fun provideAdapter(): FreelancersAdapter
    fun loadFreelancerDetails()
}