package com.kseniabl.cardsmarket.di

import com.kseniabl.cardsmarket.di.scope.SplashScreenActivityScope
import com.kseniabl.cardsmarket.ui.create_prod.*
import com.kseniabl.cardsmarket.ui.splash.*
import dagger.Module
import dagger.Provides

@Module
class CreateProdActivityModule {

    @Provides
    @SplashScreenActivityScope
    fun provideActivity(createProdActivity: CreateProdActivity): CreateProdActivity = createProdActivity

    @Provides
    fun provideCreateProdInteractor(createProdInteractor: CreateProdInteractor): CreateProdInteractorInterface = createProdInteractor

    @Provides
    internal fun provideCreateProdPresenter(createProdPresenter: CreateProdPresenter<CreateProdView, CreateProdInteractorInterface>)
            : CreateProdPresenterInterface<CreateProdView> = createProdPresenter
}