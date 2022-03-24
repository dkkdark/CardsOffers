package com.kseniabl.cardstasks.ui.base

import com.kseniabl.cardstasks.db.ChatModel
import com.kseniabl.cardstasks.db.MapOfChatModels
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface MessageSaveAndLoadInterface {
    fun insertData(msg: MapOfChatModels)
    fun deleteData(msg: MapOfChatModels)
    fun getAllData(): Single<MapOfChatModels>
    fun setNewList(id: String, el: ChatModel)
    fun dellAll()
}