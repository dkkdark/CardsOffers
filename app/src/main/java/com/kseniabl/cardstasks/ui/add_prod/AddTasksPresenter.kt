package com.kseniabl.cardstasks.ui.add_prod

import com.kseniabl.cardstasks.ui.base.BasePresenter
import com.kseniabl.cardstasks.ui.models.CardModel
import com.kseniabl.cardstasks.utils.CardTasksUtils
import com.kseniabl.cardtasks.ui.add_prod.AddTasksView
import javax.inject.Inject

class AddTasksPresenter<V: AddTasksView, I: AddTasksInteractorInterface> @Inject constructor(var interactor: I): BasePresenter<V>(),
    AddTasksPresenterInterface<V> {

    override fun addUsersNewCard(id: String, title: String, descr: String, active: Boolean, date: String, cost: Int, agreement: Boolean, currentTime: Long) {
        val cardId = CardTasksUtils.generateRandomKey()
        interactor.addCardToDB(CardModel(cardId, title, descr, date, currentTime, cost, active, agreement, id))
        interactor.addNewCard(cardId, id, title, descr, active, date, cost, agreement, currentTime)
    }
}