package com.kseniabl.cardtasks.ui.login

import com.kseniabl.cardtasks.ui.base.BaseView

interface LoginView: BaseView {
    fun startMainActivity()
    fun writeToken(token: String)
}