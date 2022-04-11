package com.kseniabl.cardstasks.ui.base

import androidx.lifecycle.LiveData
import co.intentservice.chatui.models.ChatMessage
import com.kseniabl.cardstasks.db.ChatModel
import com.kseniabl.cardstasks.db.MapOfChatModels
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.processors.PublishProcessor
import io.reactivex.rxjava3.subjects.PublishSubject

interface MessageSaveAndLoadInterface {
    fun insertData(msg: MapOfChatModels)
    fun getReceivedMsg(id: String): Flowable<MapOfChatModels>
    fun getAllData(id: String): Single<MapOfChatModels>
    fun setNewList(id: String, cardId: String, el: ChatModel)
    fun loadAllById(id: String): MapOfChatModels?
    fun dellAll()
    fun setList(id: String, list: MutableList<CardChatModel>): Single<Int>
}