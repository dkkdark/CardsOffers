package com.kseniabl.cardsmarket.ui.all_prods

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.kseniabl.cardsmarket.ui.base.BaseInteractor

interface ExecutorInteractorInterface: BaseInteractor {
    fun loadCards(): Task<DataSnapshot>
}