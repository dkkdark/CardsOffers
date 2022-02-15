package com.kseniabl.cardsmarket.ui.all_prods

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class ExecutorInteractor @Inject constructor(var database: DatabaseReference): ExecutorInteractorInterface {

    override fun loadCards(): Task<DataSnapshot> {
        return database.child("users").get()
    }
}