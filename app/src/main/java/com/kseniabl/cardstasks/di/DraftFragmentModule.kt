package com.kseniabl.cardstasks.di

import com.kseniabl.cardstasks.ui.add_prod.DraftFragment
import com.kseniabl.cardtasks.di.DraftFragmentProvideModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DraftFragmentModule {
    @ContributesAndroidInjector(modules = [(DraftFragmentProvideModule::class), (AddProdViewModelModule::class), (ViewModelProvideModule::class)])
    abstract fun provideDraftFragment(): DraftFragment
}