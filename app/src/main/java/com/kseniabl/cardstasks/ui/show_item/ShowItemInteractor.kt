package com.kseniabl.cardtasks.ui.show_item

import com.kseniabl.cardstasks.ui.base.RetrofitApiHolder
import com.kseniabl.cardstasks.ui.models.UserModel
import com.kseniabl.cardstasks.ui.show_item.ShowItemInteractorInterface
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class ShowItemInteractor @Inject constructor(val retrofit: Retrofit): ShowItemInteractorInterface {

    override fun loadFreelancerFromCard(id: String): Observable<UserModel> {
        val observer = retrofit.create(RetrofitApiHolder::class.java).getCardUser(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        return observer
    }
}