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
}