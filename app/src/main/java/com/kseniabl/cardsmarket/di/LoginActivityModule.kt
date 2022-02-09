package com.kseniabl.cardsmarket.di

import com.kseniabl.cardsmarket.di.scope.LoginActivityScope
import com.kseniabl.cardsmarket.ui.login.*
import com.kseniabl.cardsmarket.ui.splash.*
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