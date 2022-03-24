package com.kseniabl.cardtasks.di

import com.kseniabl.cardstasks.ui.chat.*
import com.kseniabl.cardtasks.ui.chat.*
import dagger.Module
import dagger.Provides

@Module
class ChatScreenActivityModule {

    @Provides
    fun provideChatScreenActivity(chatScreenActivity: ChatScreenActivity) : ChatScreenActivity = chatScreenActivity

    @Provides
    fun provideChatScreenInteractor(chatScreenInteractor: ChatScreenInteractor): ChatScreenInteractorInterface = chatScreenInteractor

    @Provides
    fun provideChatScreenPresenter(chatScreenPresenter: ChatScreenPresenter<ChatScreenView, ChatScreenInteractorInterface>)
            : ChatScreenPresenterInterface<ChatScreenView> = chatScreenPresenter
}