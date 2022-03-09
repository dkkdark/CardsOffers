package com.kseniabl.cardsmarket.di

import android.content.Context
import com.google.android.flexbox.FlexboxLayoutManager
import com.kseniabl.cardsmarket.di.scope.FreelancerFragmentScope
import com.kseniabl.cardsmarket.ui.all_prods.FreelancerFragment
import com.kseniabl.cardsmarket.ui.all_prods.*
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