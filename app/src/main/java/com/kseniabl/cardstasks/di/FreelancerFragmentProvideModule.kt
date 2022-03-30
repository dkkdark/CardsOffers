package com.kseniabl.cardtasks.di

import android.content.Context
import com.google.android.flexbox.FlexboxLayoutManager
import com.kseniabl.cardstasks.ui.all_prods.*
import com.kseniabl.cardtasks.di.scope.FreelancerFragmentScope
import com.kseniabl.cardtasks.ui.all_prods.*
import dagger.Module
import dagger.Provides

@Module
class FreelancerFragmentProvideModule {

    @Provides
    @FreelancerFragmentScope
    fun provideActivity(FreelancerFragment: FreelancerFragment) : FreelancerFragment = FreelancerFragment

    @Provides
    fun provideFreelancerInteractor(FreelancerInteractor: FreelancerInteractor): FreelancerInteractorInterface = FreelancerInteractor

    @Provides
    fun provideFreelancerPresenter(FreelancerPresenter: FreelancerPresenter<FreelancerView, FreelancerInteractorInterface>)
            : FreelancerPresenterCardModelInterface<FreelancerView> = FreelancerPresenter

    @Provides
    fun provideFreelancerAdapter(FreelancerPresenter: FreelancerPresenter<FreelancerView, FreelancerInteractorInterface>, context: Context, FreelancerFragment: FreelancerFragment)
            : FreelancersAdapter = FreelancersAdapter(FreelancerPresenter, context, FreelancerFragment)

    @Provides
    fun provideLayoutManager(fragment: FreelancerFragment): FlexboxLayoutManager = FlexboxLayoutManager(fragment.context)

}