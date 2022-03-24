package com.kseniabl.cardstasks.db

import androidx.room.TypeConverter
import co.intentservice.chatui.models.ChatMessage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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

object ConvertersArrayList {

    var gson: Gson = Gson()

    @TypeConverter
    @JvmStatic
    fun stringToSomeObjectList(data: String?): ArrayList<String> {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    @JvmStatic
    fun someObjectListToString(someObjects: ArrayList<String>?): String {
        return gson.toJson(someObjects)
    }
}