package com.kseniabl.cardsmarket.di

import com.kseniabl.cardsmarket.ui.settings.SettingsFragmnet
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SettingsFragmentModule {

    @ContributesAndroidInjector(modules = [(SettingsFragmentProvideModule::class)])
    abstract fun provideSettingsFragment(): SettingsFragmnet
}