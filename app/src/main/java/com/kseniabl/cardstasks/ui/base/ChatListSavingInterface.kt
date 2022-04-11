package com.kseniabl.cardstasks.ui.base

import com.kseniabl.cardstasks.ui.chat.ChatListAdapter
import com.kseniabl.cardstasks.ui.chat.ChatWithModel
import io.reactivex.rxjava3.processors.PublishProcessor
import io.reactivex.rxjava3.processors.ReplayProcessor

interface ChatListSavingInterface {
    fun getChatList(): ArrayList<ChatWithModel>?
    fun saveChatList(listOfChats: ArrayList<ChatWithModel>)
    fun getIsListChangedObservable(): PublishProcessor<String>
}