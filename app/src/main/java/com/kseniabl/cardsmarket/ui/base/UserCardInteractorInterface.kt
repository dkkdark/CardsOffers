package com.kseniabl.cardsmarket.ui.base

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference

interface UserCardInteractorInterface {
    fun loadCards(): Task<DataSnapshot>?
    fun childChanged(): DatabaseReference?
    fun getUserId(): String?
}