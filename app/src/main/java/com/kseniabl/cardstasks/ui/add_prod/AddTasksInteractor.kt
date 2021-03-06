package com.kseniabl.cardstasks.ui.add_prod

import android.util.Log
import com.kseniabl.cardstasks.db.RepositoryInterface
import com.kseniabl.cardstasks.ui.base.RetrofitApiHolder
import com.kseniabl.cardstasks.ui.base.UserCardInteractor
import com.kseniabl.cardstasks.ui.base.UsersCards
import com.kseniabl.cardstasks.ui.models.CardModel
import com.kseniabl.cardtasks.ui.models.MessageModel
import com.kseniabl.cardstasks.utils.CardTasksUtils.generateRandomKey
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class AddTasksInteractor @Inject constructor(var retrofit: Retrofit, val repository: RepositoryInterface): AddTasksInteractorInterface, UserCardInteractor() {

    override fun addCardToDB(card: CardModel) {
        repository.insertAddProdCard(card)
    }

    override fun addNewCard(cardId: String, id: String, title: String, descr: String, active: Boolean, date: String, cost: Int, agreement: Boolean, currentTime: Long) {
        /*retrofit.create(RetrofitApiHolder::class.java).addNewCard(id, cardId, title, descr, date, currentTime, cost, active, agreement)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MessageModel> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(data: MessageModel?) {
                    if (data?.message == "success") {
                        Log.e("qqq", "id = $id")
                        val card = CardModel(cardId, title, descr, date, currentTime, cost, active, agreement, id)
                        UsersCards.addCard(card)
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.e("qqq", "baseInfo show onError ${e?.message}")
                }

                override fun onComplete() {
                }
            })*/
    }
}