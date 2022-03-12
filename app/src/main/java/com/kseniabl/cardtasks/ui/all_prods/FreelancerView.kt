package com.kseniabl.cardtasks.ui.all_prods

import com.kseniabl.cardtasks.ui.base.BaseView

interface FreelancerView: BaseView {
    fun provideAdapter(): FreelancersAdapter
    fun loadFreelancerDetails()
}