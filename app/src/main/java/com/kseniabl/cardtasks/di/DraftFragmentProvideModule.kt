package com.kseniabl.cardtasks.di

import android.content.Context
import com.google.android.flexbox.FlexboxLayoutManager
import com.kseniabl.cardtasks.di.scope.DraftFragmentScope
import com.kseniabl.cardtasks.ui.add_prod.*
import dagger.Module
import dagger.Provides

@Module
class DraftFragmentProvideModule {

    @Provides
    @DraftFragmentScope
    fun provideActivity(draftFragment: DraftFragment) : DraftFragment = draftFragment

    @Provides
    fun provideDraftInteractor(draftInteractor: DraftInteractor): DraftInteractorInterface = draftInteractor

    @Provides
    fun provideDraftPresenter(draftPresenter: DraftPresenter<DraftView, DraftInteractorInterface>)
            : DraftPresenterInterface<DraftView> = draftPresenter

    @Provides
    fun provideDraftFragmentAdapter(draftPresenter: DraftPresenter<DraftView, DraftInteractorInterface>, context: Context, draftFragment: DraftFragment)
            : DraftAdapter = DraftAdapter(draftPresenter, context, draftFragment)

    @Provides
    fun provideDraftLayoutManager(fragment: DraftFragment): FlexboxLayoutManager = FlexboxLayoutManager(fragment.context)
}