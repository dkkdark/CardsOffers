package com.kseniabl.cardstasks.ui.show_item

import android.content.Context
import android.util.Base64
import android.util.Log
import android.widget.TextView
import com.bumptech.glide.Glide
import com.idlestar.ratingstar.RatingStarView
import com.kseniabl.cardstasks.ui.base.BasePresenter
import com.kseniabl.cardstasks.ui.base.CurrentUserClassInterface
import com.kseniabl.cardstasks.ui.base.ImageModel
import com.kseniabl.cardstasks.ui.models.UserModel
import com.kseniabl.cardtasks.R
import com.kseniabl.cardtasks.ui.show_item.ShowItemView
import com.mikhaellopez.circularimageview.CircularImageView
import io.reactivex.rxjava3.core.FlowableSubscriber
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import org.reactivestreams.Subscription
import javax.inject.Inject

class ShowItemPresenter<V: ShowItemView, I: ShowItemInteractorInterface> @Inject constructor(val interactor: I, var currentUserClass: CurrentUserClassInterface,
                                                                                             var context: Context): ShowItemPresenterInterface<V>, BasePresenter<V>() {

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

    override fun setupFreelancerImage(id: String, imageViewProfile: CircularImageView) {
        interactor.getProfileImage(id).subscribe(object :
            FlowableSubscriber<ImageModel> {
            override fun onSubscribe(s: Subscription) {
                s.request(Long.MAX_VALUE)
            }

            override fun onNext(data: ImageModel) {
                val bytes = Base64.decode(data.img, Base64.DEFAULT)
                Glide.with(context).load(bytes).placeholder(R.drawable.user).into(imageViewProfile)
            }

            override fun onError(t: Throwable) {
                Log.e("qqq", "setupProfileImage onError ${t.message}")
            }

            override fun onComplete() {
            }

        })
    }

}