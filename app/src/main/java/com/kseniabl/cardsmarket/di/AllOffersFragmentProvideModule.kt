package com.kseniabl.cardsmarket.di

import android.content.Context
import com.google.android.flexbox.FlexboxLayoutManager
import com.kseniabl.cardsmarket.di.scope.AllProdsFragmentScope
import com.kseniabl.cardsmarket.ui.all_prods.*
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