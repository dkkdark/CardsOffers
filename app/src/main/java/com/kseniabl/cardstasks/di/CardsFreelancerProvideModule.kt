package com.kseniabl.cardstasks.di

import android.content.Context
import com.google.android.flexbox.FlexboxLayoutManager
import com.kseniabl.cardstasks.di.scope.CardsFreelancerScope
import com.kseniabl.cardstasks.ui.freelancer_details.*
import dagger.Module
import dagger.Provides

@Module
class CardsFreelancerProvideModule {

    @Provides
    @CardsFreelancerScope
    fun provideCardsFreelancerFragment(cardsFreelancerFragment: CardsFreelancerFragment) : CardsFreelancerFragment = cardsFreelancerFragment

    @Provides
    fun provideCardsFreelancerInteractor(cardsFreelancerInteractor: CardsFreelancerInteractor): CardsFreelancerInteractorInterface = cardsFreelancerInteractor

    @Provides
    fun provideCardsFreelancerPresenter(cardsFreelancerPresenter: CardsFreelancerPresenter<CardsFreelancerView, CardsFreelancerInteractorInterface>)
            : CardsFreelancerPresenterInterface<CardsFreelancerView> = cardsFreelancerPresenter

    @Provides
    fun provideAllProdsAdapter(chatFreelancerPresenter: CardsFreelancerPresenter<CardsFreelancerView, CardsFreelancerInteractorInterface>, context: Context, cardsFreelancerFragment: CardsFreelancerFragment)
            : CardsFreelancerAdapter = CardsFreelancerAdapter(chatFreelancerPresenter, context, cardsFreelancerFragment)

    @Provides
    fun provideLayoutManager(fragment: CardsFreelancerFragment): FlexboxLayoutManager = FlexboxLayoutManager(fragment.context)
}