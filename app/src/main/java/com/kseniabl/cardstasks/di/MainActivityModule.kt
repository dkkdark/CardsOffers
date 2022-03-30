package com.kseniabl.cardtasks.di

import com.kseniabl.cardstasks.ui.main.MainActivity
import com.kseniabl.cardstasks.ui.main.MainView
import com.kseniabl.cardtasks.di.scope.MainActivityScope
import com.kseniabl.cardtasks.ui.main.*
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