package com.kseniabl.cardtasks.di

import com.kseniabl.cardstasks.ui.settings.SettingsFragmnet
import com.kseniabl.cardstasks.ui.settings.SettingsInteractor
import com.kseniabl.cardstasks.ui.settings.SettingsView
import com.kseniabl.cardtasks.di.scope.SettingsFragmentScope
import com.kseniabl.cardtasks.ui.settings.*
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