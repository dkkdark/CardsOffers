package com.kseniabl.cardtasks.di

import com.kseniabl.cardtasks.ui.add_prod.*
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