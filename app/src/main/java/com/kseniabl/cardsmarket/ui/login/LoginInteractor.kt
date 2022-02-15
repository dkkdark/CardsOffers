package com.kseniabl.cardsmarket.ui.login

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.kseniabl.cardsmarket.ui.base.UserCardInteractor
import javax.inject.Inject

class LoginInteractor @Inject constructor(): LoginInteractorInterface, UserCardInteractor() {

    override fun createUserWithEmail(email: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }

    override fun singInWithEmail(email: String, password: String): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password)
    }

    override fun getCards() = loadCards()

    override fun setProfileInfo(name: String) {
        val userId = getUserId()
        if (userId != null) {
            database.child("users").child(userId).child("profileInfo").child("name")
                .setValue(name)
            database.child("users").child(userId).child("profileInfo").child("rating")
                .setValue(0F)
            database.child("users").child(userId).child("profileInfo").child("isExecutor")
                .setValue(false)
            database.child("users").child(userId).child("profileInfo").child("profession")
                .child("specialization").setValue("")
            database.child("users").child(userId).child("profileInfo").child("profession")
                .child("description").setValue("")
            database.child("users").child(userId).child("profileInfo").child("profession")
                .child("tags").setValue("")
            database.child("users").child(userId).child("profileInfo").child("additional")
                .child("description").setValue("")
            database.child("users").child(userId).child("profileInfo").child("additional")
                .child("country").setValue("")
            database.child("users").child(userId).child("profileInfo").child("additional")
                .child("city").setValue("")
            database.child("users").child(userId).child("profileInfo").child("additional")
                .child("typeOfWork").setValue("")
        }
    }
}