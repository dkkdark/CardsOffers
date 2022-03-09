package com.kseniabl.cardsmarket.ui.show_item

import android.util.Log
import com.kseniabl.cardsmarket.ui.base.RetrofitApiHolder
import com.kseniabl.cardsmarket.ui.models.MessageModel
import com.kseniabl.cardsmarket.ui.models.UserModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
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