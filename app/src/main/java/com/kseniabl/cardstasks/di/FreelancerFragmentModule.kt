package com.kseniabl.cardtasks.di

import com.kseniabl.cardstasks.ui.all_prods.FreelancerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FreelancerFragmentModule {

    @ContributesAndroidInjector(modules = [(FreelancerFragmentProvideModule::class)])
    abstract fun provideFreelancerFragment(): FreelancerFragment
}