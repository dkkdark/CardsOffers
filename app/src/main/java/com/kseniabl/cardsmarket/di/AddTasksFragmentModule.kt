package com.kseniabl.cardsmarket.di

import com.kseniabl.cardsmarket.ui.add_prod.AddTasksFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AddTasksFragmentModule {
    @ContributesAndroidInjector(modules = [(AddTasksFragmentProvideModule::class)])
    abstract fun provideAddTasksFragment(): AddTasksFragment
}