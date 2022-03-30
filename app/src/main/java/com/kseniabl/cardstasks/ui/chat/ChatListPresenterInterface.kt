package com.kseniabl.cardstasks.ui.chat

import com.kseniabl.cardtasks.ui.base.PresenterInterface
import com.kseniabl.cardtasks.ui.chat.ChatListView

interface ChatListPresenterInterface<V: ChatListView>: PresenterInterface<V>, AdapterFunctionsChatWithModeInterface {
}