package com.kseniabl.cardtasks.di

import com.kseniabl.cardtasks.ui.all_prods.*
import dagger.Module
import dagger.Provides

@Module
class AllOffersFragmentProvideModule {

    @Provides
    fun provideAllOffersFragment(allOffersFragment: AllOffersFragment) : AllOffersFragment = allOffersFragment

    @Provides
    fun provideAllProdsPresenter(allOffersPresenter: AllOffersPresenter<AllOffersView>)
            : AllOffersPresenterInterface<AllOffersView> = allOffersPresenter

}