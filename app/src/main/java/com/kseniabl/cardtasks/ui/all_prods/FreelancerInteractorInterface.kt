package com.kseniabl.cardtasks.ui.all_prods

import com.kseniabl.cardtasks.ui.base.BaseInteractor
import com.kseniabl.cardtasks.ui.models.UserModel
import io.reactivex.rxjava3.core.Observable

interface FreelancerInteractorInterface: BaseInteractor {
    fun loadFreelancers(): Observable<List<UserModel>>
}