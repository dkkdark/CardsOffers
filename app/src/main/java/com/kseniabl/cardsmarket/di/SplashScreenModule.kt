package com.kseniabl.cardsmarket.di

import com.kseniabl.cardsmarket.di.scope.SplashScreenActivityScope
import com.kseniabl.cardsmarket.ui.splash.*
import dagger.Module
import dagger.Provides

@Module
class SplashScreenModule {

    @Provides
    @SplashScreenActivityScope
    fun provideActivity(splashScreenActivity: SplashScreenActivity): SplashScreenActivity = splashScreenActivity

    @Provides
    fun provideSplashInteractor(splashInteractor: SplashInteractor): SplashInteractorInterface = splashInteractor

    @Provides
    internal fun provideSplashPresenter(splashPresenter: SplashPresenter<SplashView, SplashInteractorInterface>)
            : SplashPresenterInterface<SplashView> = splashPresenter
}