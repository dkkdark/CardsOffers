package com.kseniabl.cardtasks.di

import com.kseniabl.cardtasks.ui.chat.ChatListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ChatListFragmentModule {

    @ContributesAndroidInjector(modules = [(ChatListFragmentProvideModule::class)])
    abstract fun provideChatListFragment(): ChatListFragment
}