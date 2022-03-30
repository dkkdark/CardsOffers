package com.kseniabl.cardtasks.di

import com.kseniabl.cardstasks.di.AddTasksFragmentProvideModule
import com.kseniabl.cardstasks.ui.add_prod.AddTasksFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AddTasksFragmentModule {
    @ContributesAndroidInjector(modules = [(AddTasksFragmentProvideModule::class)])
    abstract fun provideAddTasksFragment(): AddTasksFragment
}