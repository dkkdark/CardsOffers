package com.kseniabl.cardstasks.ui.all_prods

import com.kseniabl.cardstasks.ui.base.FreelancerModel
import com.kseniabl.cardtasks.ui.base.BaseInteractor
import com.kseniabl.cardstasks.ui.models.UserModel
import io.reactivex.rxjava3.core.Observable

interface FreelancerInteractorInterface: BaseInteractor {
    fun loadFreelancers(): Observable<List<FreelancerModel>>
}