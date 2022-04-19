package com.kseniabl.cardstasks.ui.dialogs

import android.util.Log
import com.kseniabl.cardstasks.ui.base.RetrofitApiHolder
import com.kseniabl.cardstasks.ui.models.CardModel
import com.kseniabl.cardtasks.ui.models.MessageModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class CreateNewTaskInteractor @Inject constructor(val retrofit: Retrofit): CreateNewTaskInteractorInterface {

    override fun deleteTaskFromServer(id: String, cardId: String): Flowable<MessageModel> {
        return retrofit.create(RetrofitApiHolder::class.java).deleteCard(id, cardId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}