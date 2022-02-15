package com.kseniabl.cardsmarket.di

import android.content.Context
import com.google.android.flexbox.FlexboxLayoutManager
import com.kseniabl.cardsmarket.di.scope.ExecutorFragmentScope
import com.kseniabl.cardsmarket.ui.all_prods.ExecutorFragment
import com.kseniabl.cardsmarket.ui.all_prods.*
import dagger.Module
import dagger.Provides

@Module
class ExecutorFragmentProvideModule {

    @Provides
    @ExecutorFragmentScope
    fun provideActivity(executorFragment: ExecutorFragment) : ExecutorFragment = executorFragment

    @Provides
    fun provideExecutorInteractor(executorInteractor: ExecutorInteractor): ExecutorInteractorInterface = executorInteractor

    @Provides
    fun provideExecutorPresenter(executorPresenter: ExecutorPresenter<ExecutorView, ExecutorInteractorInterface>)
            : ExecutorPresenterCardModelInterface<ExecutorView> = executorPresenter

    @Provides
    fun provideExecutorAdapter(executorPresenter: ExecutorPresenter<ExecutorView, ExecutorInteractorInterface>, context: Context, executorFragment: ExecutorFragment)
            : ExecutorsAdapter = ExecutorsAdapter(executorPresenter, context, executorFragment)

    @Provides
    fun provideLayoutManager(fragment: ExecutorFragment): FlexboxLayoutManager = FlexboxLayoutManager(fragment.context)

}