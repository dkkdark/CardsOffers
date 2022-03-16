package com.kseniabl.cardtasks.di

import com.kseniabl.cardstasks.ui.freelancer_details.InfoFreelancerFragment
import com.kseniabl.cardtasks.ui.freelancer_details.*
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