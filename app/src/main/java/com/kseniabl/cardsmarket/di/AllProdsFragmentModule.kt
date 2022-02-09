package com.kseniabl.cardsmarket.di

import com.kseniabl.cardsmarket.ui.all_prods.AllProdsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AllProdsFragmentModule {

    @ContributesAndroidInjector(modules = [(AllProdsFragmentProvideModule::class)])
    abstract fun provideAllProdsFragment(): AllProdsFragment
}