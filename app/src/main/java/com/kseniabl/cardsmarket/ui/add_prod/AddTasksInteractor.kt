package com.kseniabl.cardsmarket.ui.add_prod

import com.kseniabl.cardsmarket.ui.base.UserCardInteractor
import java.util.*
import javax.inject.Inject

class AddTasksInteractor @Inject constructor(): AddTasksInteractorInterface, UserCardInteractor() {

    override fun addNewCard(title: String, descr: String, active: Boolean, date: String, cost: String, agreement: Boolean, currentTime: Long) {
        childChanged()?.push()?.let {
            it.child("title").setValue(title)
            it.child("description").setValue(descr)
            it.child("active").setValue(active)
            it.child("date").setValue(date)
            it.child("cost").setValue(cost)
            it.child("agreement").setValue(agreement)
            it.child("createTime").setValue(currentTime)
        }
    }
}