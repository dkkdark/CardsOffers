package com.kseniabl.cardtasks.ui.splash

import com.kseniabl.cardtasks.ui.base.BaseView

interface SplashView: BaseView {
    fun openMainActivity()
    fun openLoginActivity()
    fun readToken(): String?
}