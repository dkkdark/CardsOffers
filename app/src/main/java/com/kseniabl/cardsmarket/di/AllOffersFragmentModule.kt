package com.kseniabl.cardsmarket.di

import com.kseniabl.cardsmarket.ui.all_prods.AllOffersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AllOffersFragmentModule {

    @ContributesAndroidInjector(modules = [(AllOffersFragmentProvideModule::class)])
    abstract fun provideAllOffersFragment(): AllOffersFragment
}