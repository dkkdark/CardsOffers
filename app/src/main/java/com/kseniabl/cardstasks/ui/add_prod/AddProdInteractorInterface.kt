package com.kseniabl.cardstasks.ui.add_prod

import com.kseniabl.cardtasks.ui.add_prod.AddProdsAdapter
import com.kseniabl.cardtasks.ui.base.BaseInteractor
import com.kseniabl.cardtasks.ui.models.MessageModel
import io.reactivex.rxjava3.core.Flowable

interface AddProdInteractorInterface: BaseInteractor {
    fun observeCards(recyclerAdapter: AddProdsAdapter)
    fun loadCards(id: String, recyclerAdapter: AddProdsAdapter)
    fun changeCard(id: String, cardId: String, title: String, descr: String, date: String, createTime: Long,
                   cost: Int, active: Boolean, agreement: Boolean)
    fun deleteCard(userId: String, cardId: String): Flowable<MessageModel>
}