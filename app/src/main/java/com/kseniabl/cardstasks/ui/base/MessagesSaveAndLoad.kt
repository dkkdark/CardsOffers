package com.kseniabl.cardstasks.ui.base

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import co.intentservice.chatui.models.ChatMessage
import com.kseniabl.cardstasks.db.ChatModel
import com.kseniabl.cardstasks.db.ChatRepository
import com.kseniabl.cardstasks.db.MapOfChatModels
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.processors.BehaviorProcessor
import io.reactivex.rxjava3.processors.PublishProcessor
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
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

    override fun getReceivedMsg(id: String): Flowable<MapOfChatModels> {
        return chatRepo.loadReceivedMessages(id)
    }

    override fun getAllData(id: String): Single<MapOfChatModels> {
        return chatRepo.getAllMsg(id)
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

     override fun loadAllById(id: String): MapOfChatModels? {
        return chatRepo.loadAllById(id)
    }

    override fun dellAll() {
        GlobalScope.launch {
            for (el in chatRepo.loadAll())
                chatRepo.deleteMsg(el)
        }
    }
}