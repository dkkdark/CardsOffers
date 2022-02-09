package com.kseniabl.cardsmarket.di

import com.kseniabl.cardsmarket.ui.show_item.ShowItemFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ShowItemFragmentModule {

    @ContributesAndroidInjector(modules = [(ShowItemFragmentProvideModule::class)])
    abstract fun provideShowItemFragment(): ShowItemFragment
}