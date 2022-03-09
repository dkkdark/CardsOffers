package com.kseniabl.cardsmarket.ui.all_prods

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.kseniabl.cardsmarket.ui.base.BaseInteractor
import com.kseniabl.cardsmarket.ui.models.UserModel
import io.reactivex.rxjava3.core.Observable

interface FreelancerInteractorInterface: BaseInteractor {
    fun loadFreelancers(): Observable<List<UserModel>>
}