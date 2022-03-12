package com.kseniabl.cardtasks.di

import com.kseniabl.cardtasks.ui.chat.ChatScreenActivity
import com.kseniabl.cardtasks.ui.chat.ChatScreenPresenter
import com.kseniabl.cardtasks.ui.chat.ChatScreenPresenterInterface
import com.kseniabl.cardtasks.ui.chat.ChatScreenView
import dagger.Module
import dagger.Provides

@Module
class ChatScreenActivityModule {

    @Provides
    fun provideChatScreenActivity(chatScreenActivity: ChatScreenActivity) : ChatScreenActivity = chatScreenActivity

    @Provides
    fun provideChatScreenPresenter(chatScreenPresenter: ChatScreenPresenter<ChatScreenView>)
            : ChatScreenPresenterInterface<ChatScreenView> = chatScreenPresenter
}