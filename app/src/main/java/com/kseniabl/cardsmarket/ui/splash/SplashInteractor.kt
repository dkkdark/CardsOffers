package com.kseniabl.cardsmarket.ui.splash

import com.google.firebase.auth.FirebaseAuth
import com.kseniabl.cardsmarket.ui.base.UserCardInteractor
import javax.inject.Inject

class SplashInteractor @Inject constructor(): SplashInteractorInterface, UserCardInteractor() {

    override fun isUserLogin(auth: FirebaseAuth): Boolean {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            return true
        }
        return false
    }

    override fun getCards() = loadCards()
}