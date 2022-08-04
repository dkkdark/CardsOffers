package com.kseniabl.cardstasks.ui.all_prods

import com.kseniabl.cardstasks.ui.base.FreelancerModel
import com.kseniabl.cardstasks.ui.base.ImageModel
import com.kseniabl.cardstasks.ui.base.RetrofitApiHolder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class FreelancerInteractor @Inject constructor(var retrofit: Retrofit):
    FreelancerInteractorInterface {

    override fun loadFreelancers(): Observable<List<FreelancerModel>> {
        val observable = retrofit.create(RetrofitApiHolder::class.java).getAllFreelancers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        return observable
    }

    override fun loadFreelancerImg(id: String): Flowable<ImageModel> {
        val observable = retrofit.create(RetrofitApiHolder::class.java).getImg(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        return observable
    }
}