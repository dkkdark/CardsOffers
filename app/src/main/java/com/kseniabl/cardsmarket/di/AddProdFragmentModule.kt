package com.kseniabl.cardsmarket.di

import com.kseniabl.cardsmarket.ui.add_prod.AddProdFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AddProdFragmentModule {

    @ContributesAndroidInjector(modules = [(AddProdFragmentProvideModule::class)])
    abstract fun provideAddProdFragment(): AddProdFragment
}