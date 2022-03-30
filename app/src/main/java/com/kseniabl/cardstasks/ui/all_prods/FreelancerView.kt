package com.kseniabl.cardstasks.ui.all_prods

import com.kseniabl.cardstasks.ui.base.FreelancerModel
import com.kseniabl.cardstasks.ui.models.UserModel
import com.kseniabl.cardtasks.ui.all_prods.FreelancersAdapter
import com.kseniabl.cardtasks.ui.base.BaseView

interface FreelancerView: BaseView {
    fun provideAdapter(): FreelancersAdapter
    fun loadFreelancerDetails(item: FreelancerModel)
}