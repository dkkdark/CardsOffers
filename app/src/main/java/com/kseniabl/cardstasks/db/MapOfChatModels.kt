package com.kseniabl.cardstasks.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MapOfChatModels (
    @PrimaryKey var userId: String,
    var chatModelList: MutableList<ChatModel> = arrayListOf()
)