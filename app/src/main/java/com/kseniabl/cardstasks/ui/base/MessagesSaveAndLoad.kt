package com.kseniabl.cardstasks.ui.base

import android.content.Context
import android.util.Log
import com.kseniabl.cardstasks.db.ChatModel
import com.kseniabl.cardstasks.db.ChatRepository
import com.kseniabl.cardstasks.db.MapOfChatModels
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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

    override fun getReceivedMsg(id: String): Flowable<MapOfChatModels> {
        return chatRepo.loadReceivedMessages(id)
    }

    override fun getAllData(id: String): Single<MapOfChatModels> {
        return chatRepo.getAllMsg(id)
    }

    override fun setNewList(id: String, cardId: String, el: ChatModel) {
        GlobalScope.launch {
            val model = loadAllById(id)
            val list = arrayListOf<ChatModel>()
            if (model != null) {
                val cardChatList = model.cardChatModelList
                val listForDelete = arrayListOf<CardChatModel>()

                for (card in model.cardChatModelList) {
                    if (card.cardId == cardId) {
                        list.addAll(card.chatList)
                        listForDelete.add(card)
                    }
                }
                cardChatList.removeAll(listForDelete)
                list.add(el)
                cardChatList.add(CardChatModel(cardId, list))

                chatRepo.insertMsg(MapOfChatModels(id, cardChatList))
            }
            else {
                chatRepo.insertMsg(MapOfChatModels(id, mutableListOf(CardChatModel(cardId, mutableListOf(el)))))
            }
        }
    }

     override fun loadAllById(id: String): MapOfChatModels? {
        return chatRepo.loadAllById(id)
    }

    override fun dellAll() {
        GlobalScope.launch {
            for (el in chatRepo.loadAll())
                chatRepo.deleteMsg(el)
        }
    }

    override fun setList(id: String, list: MutableList<CardChatModel>): Single<Int> {
        return chatRepo.setList(id, list)
    }
}