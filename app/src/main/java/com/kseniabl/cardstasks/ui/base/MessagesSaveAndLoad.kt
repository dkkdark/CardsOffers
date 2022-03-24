package com.kseniabl.cardstasks.ui.base

import android.content.Context
import android.util.Log
import com.kseniabl.cardstasks.db.ChatModel
import com.kseniabl.cardstasks.db.ChatRepository
import com.kseniabl.cardstasks.db.MapOfChatModels
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.NullPointerException
import javax.inject.Inject

class MessagesSaveAndLoad @Inject constructor(var context: Context): MessageSaveAndLoadInterface {

    private var chatRepo: ChatRepository = ChatRepository(context)

    override fun insertData(msg: MapOfChatModels) {
        GlobalScope.launch {
            val model = loadAllById(msg.userId)
            if (model == null)
                chatRepo.insertMsg(msg)
        }
    }

    override fun deleteData(msg: MapOfChatModels) {
        GlobalScope.launch {
            chatRepo.deleteMsg(msg)
        }
    }

    override fun getAllData(): Single<MapOfChatModels> {
        return chatRepo.getAllMsg()
    }

    override fun setNewList(id: String, el: ChatModel) {
        GlobalScope.launch {
            val model = loadAllById(id)
            val list = arrayListOf<ChatModel>()
            if (model?.chatModelList?.isNullOrEmpty() == false)
                list.addAll(model.chatModelList)
            list.add(el)
            chatRepo.insertMsg(MapOfChatModels(id, list))
        }
    }

     private fun loadAllById(id: String): MapOfChatModels? {
        return chatRepo.loadAllById(id)
    }

    override fun dellAll() {
        GlobalScope.launch {
            if (chatRepo.loadAll() != null)
                chatRepo.deleteMsg(chatRepo.loadAll())
        }
    }
}