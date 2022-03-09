package com.kseniabl.cardsmarket.ui.all_prods

import com.kseniabl.cardsmarket.ui.base.RetrofitApiHolder
import com.kseniabl.cardsmarket.ui.models.UserModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class FreelancerInteractor @Inject constructor(var retrofit: Retrofit): FreelancerInteractorInterface {

    override fun loadFreelancers(): Observable<List<UserModel>> {
        val observable = retrofit.create(RetrofitApiHolder::class.java).getAllFreelancers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        return observable
    }
}