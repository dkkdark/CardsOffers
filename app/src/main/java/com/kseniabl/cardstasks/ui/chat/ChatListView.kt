package com.kseniabl.cardstasks.ui.chat

import com.kseniabl.cardstasks.ui.chat.ChatListAdapter
import com.kseniabl.cardtasks.ui.base.BaseView

interface ChatListView: BaseView {
    fun provideAdapter(): ChatListAdapter
    fun startChatScreenActivity(id: String, card_id: String, card_title: String, card_cost: String)
}