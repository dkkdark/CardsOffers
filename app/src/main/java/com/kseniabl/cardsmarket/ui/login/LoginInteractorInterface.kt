package com.kseniabl.cardsmarket.ui.login

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.database.DataSnapshot
import com.kseniabl.cardsmarket.ui.base.BaseInteractor

interface LoginInteractorInterface: BaseInteractor {
    fun createUserWithEmail(email: String, password: String): Task<AuthResult>
    fun singInWithEmail(email: String, password: String): Task<AuthResult>
    fun getCards(): Task<DataSnapshot>?
}