package com.kseniabl.cardsmarket.ui.show_item

import android.util.Log
import android.widget.TextView
import com.idlestar.ratingstar.RatingStarView
import com.kseniabl.cardsmarket.ui.base.BasePresenter
import com.kseniabl.cardsmarket.ui.models.UserModel
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class ShowItemPresenter<V: ShowItemView, I: ShowItemInteractorInterface> @Inject constructor(val interactor: I): ShowItemPresenterInterface<V>, BasePresenter<V>() {

    override fun loadExecutor(id: String, nameText: TextView, specializationText: TextView, itemExeRating: RatingStarView) {
        interactor.loadExecutorFromCard(id).subscribe(object : Observer<UserModel> {
            override fun onSubscribe(d: Disposable?) {
            }

            override fun onNext(data: UserModel) {
                nameText.text = data.username
                specializationText.text = data.profession.specialization
                itemExeRating.rating = data.rating
            }

            override fun onError(e: Throwable?) {
                Log.e("qqq", "onError ${e?.message}")
            }

            override fun onComplete() {
            }

        })
    }
}