package com.kseniabl.cardsmarket.di

import com.kseniabl.cardsmarket.ui.all_prods.FreelancerFragment
import com.kseniabl.cardsmarket.ui.freelancer_details.CardsFreelancerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CardsFreelancerModule {

    @ContributesAndroidInjector(modules = [(CardsFreelancerProvideModule::class)])
    abstract fun provideCardsFreelancerFragment(): CardsFreelancerFragment
}