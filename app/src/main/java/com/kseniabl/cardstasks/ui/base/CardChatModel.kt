package com.kseniabl.cardstasks.ui.base

import com.kseniabl.cardstasks.db.db_models.ChatModel

data class CardChatModel (
    var cardId: String,
    var chatList: MutableList<ChatModel> = arrayListOf()
)