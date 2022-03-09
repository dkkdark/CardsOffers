package com.kseniabl.cardsmarket.di

import com.kseniabl.cardsmarket.ui.add_prod.DraftFragment
import com.kseniabl.cardsmarket.ui.freelancer_details.InfoFreelancerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InfoFreelanceFragmentModule {
    @ContributesAndroidInjector(modules = [(InfoFreelanceFragmentProvideModule::class)])
    abstract fun provideInfoFreelancerFragment(): InfoFreelancerFragment
}