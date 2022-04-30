package com.kseniabl.cardstasks.ui.add_prod

import com.kseniabl.cardtasks.ui.add_prod.AddTasksView
import com.kseniabl.cardtasks.ui.base.PresenterInterface

interface AddTasksPresenterInterface<V: AddTasksView>: PresenterInterface<V> {
    fun addUsersNewCard(id: String, title: String, descr: String, active: Boolean, date: String, cost: Int, agreement: Boolean, currentTime: Long)
}