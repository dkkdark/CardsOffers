package com.kseniabl.cardtasks.di

import com.kseniabl.cardstasks.di.AllProdsFragmentProvideModule
import com.kseniabl.cardstasks.ui.all_prods.AllProdsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AllProdsFragmentModule {

    @ContributesAndroidInjector(modules = [(AllProdsFragmentProvideModule::class)])
    abstract fun provideAllProdsFragment(): AllProdsFragment
}