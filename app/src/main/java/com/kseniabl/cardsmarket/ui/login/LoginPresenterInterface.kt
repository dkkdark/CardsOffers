package com.kseniabl.cardsmarket.ui.login

import com.kseniabl.cardsmarket.ui.base.PresenterInterface

interface LoginPresenterInterface<V: LoginView>: PresenterInterface<V> {
    fun createUser(name: String, email: String, password: String, passwordRep: String)
    fun singIn(email: String, password: String)
}