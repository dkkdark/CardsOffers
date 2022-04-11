package com.kseniabl.cardstasks.ui.chat

data class ChatWithModel (
    val id: String,
    val username: String,
    var lastMessage: String,
    var notSeenMessages: Int,
    val card_id: String,
    val card_title: String,
    val card_cost: String
)