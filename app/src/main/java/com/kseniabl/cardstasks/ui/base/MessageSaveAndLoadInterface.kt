package com.kseniabl.cardstasks.ui.base

import com.kseniabl.cardstasks.db.db_models.ChatModel
import com.kseniabl.cardstasks.db.db_models.MapOfChatModels
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface MessageSaveAndLoadInterface {
    fun insertData(msg: MapOfChatModels)
    fun getReceivedMsg(id: String): Flowable<MapOfChatModels>
    fun getAllData(id: String): Single<MapOfChatModels>
    fun setNewList(id: String, cardId: String, el: ChatModel)
    fun loadAllById(id: String): MapOfChatModels?
    fun dellAll()
    fun setList(id: String, list: MutableList<CardChatModel>): Single<Int>
}