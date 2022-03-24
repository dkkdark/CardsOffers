package com.kseniabl.cardtasks.di

import com.kseniabl.cardstasks.ui.all_prods.AllOffersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AllOffersFragmentModule {

    @ContributesAndroidInjector(modules = [(AllOffersFragmentProvideModule::class)])
    abstract fun provideAllOffersFragment(): AllOffersFragment
}