package com.kseniabl.cardstasks.ui.add_prod

import com.kseniabl.cardstasks.ui.models.CardModel
import com.kseniabl.cardtasks.ui.base.BaseInteractor

interface AddTasksInteractorInterface: BaseInteractor {
    fun addCardToDB(card: CardModel)
    fun addNewCard(cardId: String, id: String, title: String, descr: String, active: Boolean, date: String, cost: Int, agreement: Boolean, currentTime: Long)
}