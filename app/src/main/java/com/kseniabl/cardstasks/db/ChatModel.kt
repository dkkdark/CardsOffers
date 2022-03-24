package com.kseniabl.cardstasks.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import co.intentservice.chatui.models.ChatMessage
import kotlinx.android.parcel.Parcelize

data class ChatModel (
        val id: Int = 0,
        val message: String,
        val timestamp: Long,
        val type: ChatMessage.Type
)