package com.kseniabl.cardsmarket.di

import com.kseniabl.cardsmarket.di.scope.ShowItemScope
import com.kseniabl.cardsmarket.ui.freelancer_details.*
import com.kseniabl.cardsmarket.ui.show_item.*
import dagger.Module
import dagger.Provides

@Module
class FreelancerDetailsActivityModule {

    @Provides
    fun provideFreelancerDetailsActivity(freelancerDetailsActivity: FreelancerDetailsActivity) : FreelancerDetailsActivity = freelancerDetailsActivity

    @Provides
    fun provideFreelancerDetailsInteractor(freelancerDetailsInteractor: FreelancerDetailsInteractor): FreelancerDetailsInteractorInterface = freelancerDetailsInteractor

    @Provides
    fun provideShowItemPresenter(freelancerDetailsPresenter: FreelancerDetailsPresenter<FreelancerDetailsView, FreelancerDetailsInteractorInterface>)
            : FreelancerDetailsPresenterInterface<FreelancerDetailsView> = freelancerDetailsPresenter
}