package com.kseniabl.cardsmarket.di

import com.kseniabl.cardsmarket.ui.freelancer_details.*
import dagger.Module
import dagger.Provides

@Module
class InfoFreelanceFragmentProvideModule {

    @Provides
    fun provideInfoFreelancerFragment(infoFreelanceFragment: InfoFreelancerFragment) : InfoFreelancerFragment = infoFreelanceFragment

    @Provides
    fun provideInfoFreelancerInteractor(infoFreelanceInteractor: InfoFreelanceInteractor): InfoFreelanceInteractorInterface = infoFreelanceInteractor

    @Provides
    fun provideInfoFreelancerPresenter(infoFreelancerPresenter: InfoFreelancePresenter<InfoFreelanceView, InfoFreelanceInteractorInterface>)
            : InfoFreelancePresenterInterface<InfoFreelanceView> = infoFreelancerPresenter
}