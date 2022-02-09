package com.kseniabl.cardsmarket.ui.splash

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.kseniabl.cardsmarket.ui.base.BaseInteractor

interface SplashInteractorInterface: BaseInteractor {
    fun isUserLogin(auth: FirebaseAuth): Boolean
    fun getCards(): Task<DataSnapshot>?
}