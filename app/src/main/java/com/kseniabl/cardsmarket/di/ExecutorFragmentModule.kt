package com.kseniabl.cardsmarket.di

import com.kseniabl.cardsmarket.ui.all_prods.ExecutorFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ExecutorFragmentModule {

    @ContributesAndroidInjector(modules = [(ExecutorFragmentProvideModule::class)])
    abstract fun provideExecutorFragment(): ExecutorFragment
}