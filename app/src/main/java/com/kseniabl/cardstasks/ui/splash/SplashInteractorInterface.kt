package com.kseniabl.cardstasks.ui.splash

import com.kseniabl.cardstasks.ui.models.UserModel
import com.kseniabl.cardtasks.ui.base.BaseInteractor
import io.reactivex.rxjava3.core.Flowable

interface SplashInteractorInterface: BaseInteractor {
    fun isUserExist(token: String): Flowable<UserModel>
    fun changeToken(oldToken: String, newToken: String, id: String)
    fun loadUserCards(id: String)
}