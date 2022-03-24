package com.kseniabl.cardstasks.db

import android.content.Context
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ChatRepository(context: Context) {

    private var db = CardsTasksDatabase.getInstance(context)
    private var chatDao: ChatDao = db.chatDao()

    fun getAllMsg(): Single<MapOfChatModels> {
        return chatDao.loadAllMessages()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun insertMsg(msg: MapOfChatModels) {
        chatDao.insertMessage(msg)
    }

    fun deleteMsg(msg: MapOfChatModels) {
        chatDao.deleteMessage(msg)
    }

    fun setList(id: String, list: MutableList<ChatModel>) = chatDao.setList(id, list)

    fun loadAllById(id: String): MapOfChatModels? {
        return chatDao.loadAllById(id)
    }

    fun loadAll(): MapOfChatModels {
        return chatDao.loadAll()
    }
}