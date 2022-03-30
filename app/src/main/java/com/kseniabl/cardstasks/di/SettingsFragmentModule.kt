package com.kseniabl.cardtasks.di

import com.kseniabl.cardstasks.ui.settings.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SettingsFragmentModule {

    @ContributesAndroidInjector(modules = [(SettingsFragmentProvideModule::class)])
    abstract fun provideSettingsFragment(): SettingsFragment
}