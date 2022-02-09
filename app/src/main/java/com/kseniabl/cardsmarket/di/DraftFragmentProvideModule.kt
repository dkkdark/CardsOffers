package com.kseniabl.cardsmarket.di

import com.kseniabl.cardsmarket.ui.add_prod.DraftFragment
import com.kseniabl.cardsmarket.ui.add_prod.DraftPresenter
import com.kseniabl.cardsmarket.ui.add_prod.DraftPresenterInterface
import com.kseniabl.cardsmarket.ui.add_prod.DraftView
import dagger.Module
import dagger.Provides

@Module
class DraftFragmentProvideModule {
    @Provides
    fun provideDraftFragment(draftFragment: DraftFragment) : DraftFragment = draftFragment

    @Provides
    fun provideDraftPresenter(draftPresenter: DraftPresenter<DraftView>)
            : DraftPresenterInterface<DraftView> = draftPresenter
}