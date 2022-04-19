package com.kseniabl.cardstasks.di

import com.kseniabl.cardstasks.di.scope.CardNewTask
import com.kseniabl.cardstasks.ui.dialogs.*
import dagger.Module
import dagger.Provides

@Module
class CreateNewTaskProvideModule {
    @Provides
    @CardNewTask
    fun provideCreateNewTask(createNewTaskDialog: CreateNewTaskDialog) : CreateNewTaskDialog = createNewTaskDialog

    @Provides
    fun provideCreateNewTaskPresenter(createNewTaskPresenter: CreateNewTaskPresenter)
            : CreateNewTaskPresenterInterface = createNewTaskPresenter

    @Provides
    fun provideCreateNewTaskInteractor(cardNewTaskInteractor: CreateNewTaskInteractor): CreateNewTaskInteractorInterface = cardNewTaskInteractor

}