package com.kseniabl.cardtasks.di

import android.content.Context
import com.google.android.flexbox.FlexboxLayoutManager
import com.kseniabl.cardtasks.di.scope.AllProdsFragmentScope
import com.kseniabl.cardtasks.ui.all_prods.*
import dagger.Module
import dagger.Provides

@Module
class AllProdsFragmentProvideModule {

    @Provides
    @AllProdsFragmentScope
    fun provideActivity(allProdsFragment: AllProdsFragment) : AllProdsFragment = allProdsFragment

    @Provides
    fun provideAllProdsInteractor(allProdsInteractor: AllProdsInteractor): AllProdsInteractorInterface = allProdsInteractor

    @Provides
    fun provideAllProdsPresenter(allProdsPresenter: AllProdsPresenter<AllProdsView, AllProdsInteractorInterface>)
            : AllProdsPresenterCardModelInterface<AllProdsView> = allProdsPresenter

    @Provides
    fun provideAllProdsAdapter(allProdPresenter: AllProdsPresenter<AllProdsView, AllProdsInteractorInterface>, context: Context, allProdsFragment: AllProdsFragment)
            : AllProdsAdapter = AllProdsAdapter(allProdPresenter, context, allProdsFragment)

    @Provides
    fun provideLayoutManager(fragment: AllProdsFragment): FlexboxLayoutManager = FlexboxLayoutManager(fragment.context)
}