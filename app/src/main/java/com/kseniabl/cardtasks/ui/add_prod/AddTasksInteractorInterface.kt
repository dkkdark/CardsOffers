package com.kseniabl.cardtasks.ui.add_prod

import com.kseniabl.cardtasks.ui.base.BaseInteractor

interface AddTasksInteractorInterface: BaseInteractor {
    fun addNewCard(id: String, title: String, descr: String, active: Boolean, date: String, cost: Int, agreement: Boolean, currentTime: Long)
}