package com.kseniabl.cardstasks.di

import com.kseniabl.cardstasks.di.ChatListFragmentProvideModule
import com.kseniabl.cardstasks.ui.chat.ChatListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ChatListFragmentModule {

    @ContributesAndroidInjector(modules = [(ChatListFragmentProvideModule::class)])
    abstract fun provideChatListFragment(): ChatListFragment
}