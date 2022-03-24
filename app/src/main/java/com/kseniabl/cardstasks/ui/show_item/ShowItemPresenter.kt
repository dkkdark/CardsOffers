package com.kseniabl.cardtasks.ui.show_item

import android.util.Log
import android.widget.TextView
import com.idlestar.ratingstar.RatingStarView
import com.kseniabl.cardtasks.ui.base.BasePresenter
import com.kseniabl.cardstasks.ui.models.UserModel
import com.kseniabl.cardstasks.ui.show_item.ShowItemInteractorInterface
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class ShowItemPresenter<V: ShowItemView, I: ShowItemInteractorInterface> @Inject constructor(val interactor: I): ShowItemPresenterInterface<V>, BasePresenter<V>() {

    override fun loadFreelancer(id: String, nameText: TextView, specializationText: TextView, itemExeRating: RatingStarView) {
        interactor.loadFreelancerFromCard(id).subscribe(object : Observer<UserModel> {
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