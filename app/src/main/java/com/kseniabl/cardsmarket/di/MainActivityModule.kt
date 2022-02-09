package com.kseniabl.cardsmarket.di

import com.kseniabl.cardsmarket.di.scope.MainActivityScope
import com.kseniabl.cardsmarket.ui.main.*
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @MainActivityScope
    fun provideActivity(mainActivity: MainActivity) : MainActivity = mainActivity

    @Provides
    fun provideMainInteractor(mainInteractor: MainInteractor): MainInteractorInterface = mainInteractor

    @Provides
    internal fun provideMainPresenter(mainPresenter: MainPresenter<MainView>)
            : MainPresenterInterface<MainView> = mainPresenter
}