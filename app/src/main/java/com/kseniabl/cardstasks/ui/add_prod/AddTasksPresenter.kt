package com.kseniabl.cardtasks.ui.add_prod

import com.kseniabl.cardstasks.ui.base.BasePresenter
import javax.inject.Inject

class AddTasksPresenter<V: AddTasksView, I: AddTasksInteractorInterface> @Inject constructor(var interactor: I): BasePresenter<V>(), AddTasksPresenterInterface<V> {

    override fun addUsersNewCard(id: String, title: String, descr: String, active: Boolean, date: String, cost: Int, agreement: Boolean, currentTime: Long) {
        interactor.addNewCard(id, title, descr, active, date, cost, agreement, currentTime)

    }
}