package com.kseniabl.cardsmarket.ui.add_prod

import com.kseniabl.cardsmarket.ui.base.BaseInteractor
import java.util.*

interface AddTasksInteractorInterface: BaseInteractor {
    fun addNewCard(id: String, title: String, descr: String, active: Boolean, date: String, cost: Int, agreement: Boolean, currentTime: Long)
}