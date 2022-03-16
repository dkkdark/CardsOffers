package com.kseniabl.cardtasks.di

import com.kseniabl.cardtasks.di.scope.SplashScreenActivityScope
import com.kseniabl.cardtasks.ui.splash.*
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