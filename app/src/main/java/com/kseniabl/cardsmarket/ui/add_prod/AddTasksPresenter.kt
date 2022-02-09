package com.kseniabl.cardsmarket.ui.add_prod

import com.kseniabl.cardsmarket.ui.base.BasePresenter
import java.util.*
import javax.inject.Inject

class AddTasksPresenter<V: AddTasksView, I: AddTasksInteractorInterface> @Inject constructor(var interactor: I): BasePresenter<V>(), AddTasksPresenterInterface<V> {

    override fun addOrChangeCard(title: String, descr: String, active: Boolean, date: String, cost: String, agreement: Boolean, currentTime: Long) {
        interactor.addNewCard(title, descr, active, date, cost, agreement, currentTime)
    }
}