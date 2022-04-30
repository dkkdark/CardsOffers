package com.kseniabl.cardstasks.di

import com.kseniabl.cardstasks.di.AllProdsFragmentProvideModule
import com.kseniabl.cardstasks.di.AllProdsViewModelModule
import com.kseniabl.cardstasks.di.ViewModelProvideModule
import com.kseniabl.cardstasks.ui.all_prods.AllProdsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AllProdsFragmentModule {

    @ContributesAndroidInjector(modules = [(AllProdsFragmentProvideModule::class), (AllProdsViewModelModule::class), (ViewModelProvideModule::class)])
    abstract fun provideAllProdsFragment(): AllProdsFragment
}