package com.kseniabl.cardtasks.di

import com.kseniabl.cardtasks.ui.freelancer_details.InfoFreelancerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InfoFreelanceFragmentModule {
    @ContributesAndroidInjector(modules = [(InfoFreelanceFragmentProvideModule::class)])
    abstract fun provideInfoFreelancerFragment(): InfoFreelancerFragment
}