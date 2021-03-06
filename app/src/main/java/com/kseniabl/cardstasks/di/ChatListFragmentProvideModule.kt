package com.kseniabl.cardstasks.di

import android.content.Context
import com.google.android.flexbox.FlexboxLayoutManager
import com.kseniabl.cardstasks.di.scope.ChatListFragmentScope
import com.kseniabl.cardstasks.ui.chat.*
import com.kseniabl.cardstasks.ui.chat.ChatListView
import dagger.Module
import dagger.Provides

@Module
class ChatListFragmentProvideModule {

    @Provides
    @ChatListFragmentScope
    fun provideChatListFragment(chatListFragment: ChatListFragment) : ChatListFragment = chatListFragment

    @Provides
    fun provideChatListPresenter(chatListPresenter: ChatListPresenter<ChatListView, ChatListInteractorInterface>)
            : ChatListPresenterInterface<ChatListView> = chatListPresenter

    @Provides
    fun provideAllProdsInteractor(chatListInteractor: ChatListInteractor): ChatListInteractorInterface = chatListInteractor

    @Provides
    fun provideAllProdsAdapter(chatListPresenter: ChatListPresenter<ChatListView, ChatListInteractorInterface>, context: Context, chatListFragment: ChatListFragment)
            : ChatListAdapter = ChatListAdapter(chatListPresenter, context, chatListFragment)

    @Provides
    fun provideLayoutManager(fragment: ChatListFragment): FlexboxLayoutManager = FlexboxLayoutManager(fragment.context)
}