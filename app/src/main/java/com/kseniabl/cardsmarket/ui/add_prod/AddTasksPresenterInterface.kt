package com.kseniabl.cardsmarket.ui.add_prod

import com.kseniabl.cardsmarket.ui.base.PresenterInterface
import java.util.*

interface AddTasksPresenterInterface<V: AddTasksView>: PresenterInterface<V> {
    fun addUsersNewCard(id: String, title: String, descr: String, active: Boolean, date: String, cost: Int, agreement: Boolean, currentTime: Long)
}