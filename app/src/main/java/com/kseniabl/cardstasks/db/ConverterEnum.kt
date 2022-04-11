package com.kseniabl.cardstasks.db

import androidx.room.TypeConverter
import co.intentservice.chatui.models.ChatMessage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kseniabl.cardstasks.ui.base.CardChatModel
import java.lang.reflect.Type

object ConverterEnum {
    @TypeConverter
    fun toType(value: String) = enumValueOf<ChatMessage.Type>(value)

    @TypeConverter
    fun fromType(value: ChatMessage.Type) = value.name
}

object ConvertersMutableList {

    var gson: Gson = Gson()

    @TypeConverter
    @JvmStatic
    fun stringToObjectList(data: String?): MutableList<ChatModel> {
        val listType: Type = object : TypeToken<MutableList<ChatModel?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    @JvmStatic
    fun objectListToString(someObjects: MutableList<ChatModel>?): String {
        return gson.toJson(someObjects)
    }
}

object ConvertersMutableListCardChatModel {

    var gson: Gson = Gson()

    @TypeConverter
    @JvmStatic
    fun stringToObjectList(data: String?): MutableList<CardChatModel> {
        val listType: Type = object : TypeToken<MutableList<CardChatModel?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    @JvmStatic
    fun objectListToString(someObjects: MutableList<CardChatModel>?): String {
        return gson.toJson(someObjects)
    }
}
