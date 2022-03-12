package com.kseniabl.cardtasks.di

import com.kseniabl.cardtasks.ui.add_prod.AddProdFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AddProdFragmentModule {

    @ContributesAndroidInjector(modules = [(AddProdFragmentProvideModule::class)])
    abstract fun provideAddProdFragment(): AddProdFragment
}