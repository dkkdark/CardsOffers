package com.kseniabl.cardstasks.di

import com.kseniabl.cardstasks.ui.chat.ChatListFragment
import com.kseniabl.cardstasks.ui.dialogs.CreateNewTaskDialog
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CreateNewTaskModule {
    @ContributesAndroidInjector(modules = [(CreateNewTaskProvideModule::class)])
    abstract fun provideCreateNewTaskProvideModule(): CreateNewTaskDialog
}