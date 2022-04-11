package com.kseniabl.cardtasks.di

import com.kseniabl.cardstasks.di.AddProdFragmentProvideModule
import com.kseniabl.cardstasks.ui.add_prod.AddProdFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AddProdFragmentModule {

    @ContributesAndroidInjector(modules = [(AddProdFragmentProvideModule::class)])
    abstract fun provideAddProdFragment(): AddProdFragment
}