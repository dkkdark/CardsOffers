package com.kseniabl.cardtasks.di

import com.kseniabl.cardtasks.ui.freelancer_details.*
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