package com.kseniabl.cardtasks.ui.splash

import com.kseniabl.cardtasks.ui.base.BaseInteractor

interface SplashInteractorInterface: BaseInteractor {
    fun isUserLogin(token: String)
    fun loadUserCards(id: String)
}