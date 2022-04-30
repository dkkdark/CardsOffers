package com.kseniabl.cardstasks.db

import androidx.room.TypeConverter
import co.intentservice.chatui.models.ChatMessage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kseniabl.cardstasks.ui.base.CardChatModel
import com.kseniabl.cardstasks.ui.models.CardModel
import java.lang.reflect.Type

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

object ConvertersMutableListCardModel {

    var gson: Gson = Gson()

    @TypeConverter
    @JvmStatic
    fun stringToObjectList(data: String?): MutableList<CardModel> {
        val listType: Type = object : TypeToken<MutableList<CardModel?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    @JvmStatic
    fun objectListToString(someObjects: MutableList<CardModel>?): String {
        return gson.toJson(someObjects)
    }
}
