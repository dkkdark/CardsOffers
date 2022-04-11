package com.kseniabl.cardstasks.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kseniabl.cardstasks.ui.base.CardChatModel

@Entity
data class MapOfChatModels (
    @PrimaryKey var userId: String,
    var cardChatModelList: MutableList<CardChatModel> = arrayListOf()
)