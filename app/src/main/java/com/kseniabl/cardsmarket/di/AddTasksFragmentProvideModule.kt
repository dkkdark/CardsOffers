package com.kseniabl.cardsmarket.di

import android.content.Context
import com.google.android.flexbox.FlexboxLayoutManager
import com.kseniabl.cardsmarket.di.scope.FreelancerFragmentScope
import com.kseniabl.cardsmarket.ui.add_prod.*
import com.kseniabl.cardsmarket.ui.all_prods.*
import dagger.Module
import dagger.Provides

@Module
class AddTasksFragmentProvideModule {

    @Provides
    fun provideAddTasksFragment(addTasksFragment: AddTasksFragment) : AddTasksFragment = addTasksFragment

    @Provides
    fun provideAddTasksPresenter(addTasksPresenter: AddTasksPresenter<AddTasksView, AddTasksInteractorInterface>)
            : AddTasksPresenterInterface<AddTasksView> = addTasksPresenter

    @Provides
    fun provideAddTasksInteractor(addTasksInteractor: AddTasksInteractor): AddTasksInteractorInterface = addTasksInteractor
}