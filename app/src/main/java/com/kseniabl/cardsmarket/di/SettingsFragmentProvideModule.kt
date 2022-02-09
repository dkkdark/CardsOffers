package com.kseniabl.cardsmarket.di

import com.kseniabl.cardsmarket.di.scope.SettingsFragmentScope
import com.kseniabl.cardsmarket.ui.settings.*
import dagger.Module
import dagger.Provides

@Module
class SettingsFragmentProvideModule {

    @Provides
    @SettingsFragmentScope
    fun provideActivity(settingsFragmnet: SettingsFragmnet) : SettingsFragmnet = settingsFragmnet

    @Provides
    fun provideSettingsInteractor(settingsInteractor: SettingsInteractor): SettingsInteractorInterface = settingsInteractor

    @Provides
    fun provideSettingsPresenter(settingsPresenter: SettingsPresenter<SettingsView, SettingsInteractorInterface>)
            : SettingsPresenterInterface<SettingsView> = settingsPresenter
}