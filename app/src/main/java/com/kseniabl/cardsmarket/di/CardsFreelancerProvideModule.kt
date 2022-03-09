package com.kseniabl.cardsmarket.di

import com.kseniabl.cardsmarket.ui.freelancer_details.*
import dagger.Module
import dagger.Provides

@Module
class CardsFreelancerProvideModule {

    @Provides
    fun provideCardsFreelancerFragment(cardsFreelancerFragment: CardsFreelancerFragment) : CardsFreelancerFragment = cardsFreelancerFragment

    @Provides
    fun provideCardsFreelancerInteractor(cardsFreelancerInteractor: CardsFreelancerInteractor): CardsFreelancerInteractorInterface = cardsFreelancerInteractor

    @Provides
    fun provideCardsFreelancerPresenter(cardsFreelancerPresenter: CardsFreelancerPresenter<CardsFreelancerView, CardsFreelancerInteractorInterface>)
            : CardsFreelancerPresenterInterface<CardsFreelancerView> = cardsFreelancerPresenter

}