package com.kseniabl.cardtasks.di

import com.kseniabl.cardtasks.ui.settings.SettingsFragmnet
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SettingsFragmentModule {

    @ContributesAndroidInjector(modules = [(SettingsFragmentProvideModule::class)])
    abstract fun provideSettingsFragment(): SettingsFragmnet
}