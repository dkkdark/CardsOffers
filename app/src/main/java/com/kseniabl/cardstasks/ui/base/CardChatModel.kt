package com.kseniabl.cardstasks.ui.base

import com.kseniabl.cardstasks.db.ChatModel

data class CardChatModel (
    var cardId: String,
    var chatList: MutableList<ChatModel> = arrayListOf()
)