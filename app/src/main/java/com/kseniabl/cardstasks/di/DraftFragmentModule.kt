package com.kseniabl.cardtasks.di

import com.kseniabl.cardstasks.ui.add_prod.DraftFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DraftFragmentModule {
    @ContributesAndroidInjector(modules = [(DraftFragmentProvideModule::class)])
    abstract fun provideDraftFragment(): DraftFragment
}