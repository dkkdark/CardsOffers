package com.kseniabl.cardsmarket.ui.base

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

abstract class UserCardInteractor: UserCardInteractorInterface {

    @Inject
    lateinit var database: DatabaseReference
    @Inject
    lateinit var auth: FirebaseAuth

    override fun loadCards(): Task<DataSnapshot>? {
        val id = getUserId()

        id?.let {
            return database.child("users").child(it).get()
        }
        return null
    }

    override fun childChanged(): DatabaseReference? {
        val id = getUserId()

        id?.let {
            return database.child("users").child(it)
        }
        return null
    }

    override fun getUserId(): String? {
        val user = auth.currentUser
        var id: String? = null
        user?.let { id = user.uid }
        return id
    }
}