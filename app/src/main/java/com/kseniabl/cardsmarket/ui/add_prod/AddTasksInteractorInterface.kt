package com.kseniabl.cardsmarket.ui.add_prod

import com.kseniabl.cardsmarket.ui.base.BaseInteractor
import java.util.*

interface AddTasksInteractorInterface: BaseInteractor {
    fun addNewCard(title: String, descr: String, active: Boolean, date: String, cost: String, agreement: Boolean, currentTime: Long)
}