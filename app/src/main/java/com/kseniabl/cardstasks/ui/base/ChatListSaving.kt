package com.kseniabl.cardstasks.ui.base

import android.content.Context
import android.util.Log
import co.intentservice.chatui.models.ChatMessage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kseniabl.cardstasks.ui.chat.ChatWithModel
import com.kseniabl.cardtasks.R
import io.reactivex.rxjava3.processors.PublishProcessor
import javax.inject.Inject

class ChatListSaving @Inject constructor(var context: Context): ChatListSavingInterface {

    private val isListChanged: PublishProcessor<String> = PublishProcessor.create()

    override fun getChatList(): ArrayList<ChatWithModel>? {
        val sharedPref = context.getSharedPreferences("chatListSave", Context.MODE_PRIVATE)
        val json = sharedPref.getString(context.getString(R.string.chat_list_shared_pref), "")
        val gson = Gson()
        val type = object : TypeToken<List<ChatWithModel>>(){}.type
        return try {
            gson.fromJson(json, type)
        } catch (e: NullPointerException) {
            null
        }
    }

    override fun saveChatList(listOfChats: ArrayList<ChatWithModel>) {
        val gson = Gson()
        val json = gson.toJson(listOfChats)
        val sharedPref = context.getSharedPreferences("chatListSave", Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString(context.getString(R.string.chat_list_shared_pref), json)
            apply()
        }
    }

    override fun getIsListChangedObservable() = isListChanged
}