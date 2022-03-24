package com.kseniabl.cardtasks.di

import com.kseniabl.cardstasks.ui.login.LoginInteractor
import com.kseniabl.cardstasks.ui.login.LoginInteractorInterface
import com.kseniabl.cardstasks.ui.login.LoginPresenter
import com.kseniabl.cardstasks.ui.login.LoginView
import com.kseniabl.cardtasks.di.scope.LoginActivityScope
import com.kseniabl.cardtasks.ui.login.*
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule {

    @Provides
    @LoginActivityScope
    fun provideActivity(loginActivity: LoginActivity) : LoginActivity = loginActivity

    @Provides
    fun provideLoginInteractor(loginInteractor: LoginInteractor): LoginInteractorInterface = loginInteractor

    @Provides
    internal fun provideLoginPresenter(loginPresenter: LoginPresenter<LoginView, LoginInteractorInterface>)
            : LoginPresenterInterface<LoginView> = loginPresenter
}