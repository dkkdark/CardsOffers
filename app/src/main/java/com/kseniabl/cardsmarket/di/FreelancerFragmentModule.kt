package com.kseniabl.cardsmarket.di

import com.kseniabl.cardsmarket.ui.all_prods.FreelancerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FreelancerFragmentModule {

    @ContributesAndroidInjector(modules = [(FreelancerFragmentProvideModule::class)])
    abstract fun provideFreelancerFragment(): FreelancerFragment
}