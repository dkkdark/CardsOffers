package com.kseniabl.cardtasks.di

import com.kseniabl.cardtasks.ui.freelancer_details.CardsFreelancerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CardsFreelancerModule {

    @ContributesAndroidInjector(modules = [(CardsFreelancerProvideModule::class)])
    abstract fun provideCardsFreelancerFragment(): CardsFreelancerFragment
}