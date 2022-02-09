package com.kseniabl.cardsmarket.ui.add_prod

import com.kseniabl.cardsmarket.ui.base.PresenterInterface
import java.util.*

interface AddTasksPresenterInterface<V: AddTasksView>: PresenterInterface<V> {
    fun addOrChangeCard(title: String, descr: String, active: Boolean, date: String, cost: String, agreement: Boolean, currentTime: Long)
}