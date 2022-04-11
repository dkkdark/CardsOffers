package com.kseniabl.cardstasks.ui.chat

import com.kseniabl.cardtasks.ui.base.PresenterInterface

interface ChatListPresenterInterface<V: ChatListView>: PresenterInterface<V>, AdapterFunctionsChatWithModeInterface {
    fun setChatList()
}