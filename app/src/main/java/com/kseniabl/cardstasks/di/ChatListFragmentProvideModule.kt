package com.kseniabl.cardtasks.di

import com.kseniabl.cardtasks.ui.chat.ChatListFragment
import com.kseniabl.cardtasks.ui.chat.ChatListPresenter
import com.kseniabl.cardtasks.ui.chat.ChatListPresenterInterface
import com.kseniabl.cardtasks.ui.chat.ChatListView
import dagger.Module
import dagger.Provides

@Module
class ChatListFragmentProvideModule {

    @Provides
    fun provideChatListFragment(chatListFragment: ChatListFragment) : ChatListFragment = chatListFragment

    @Provides
    fun provideChatListPresenter(chatListPresenter: ChatListPresenter<ChatListView>)
            : ChatListPresenterInterface<ChatListView> = chatListPresenter
}