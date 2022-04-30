package com.kseniabl.cardstasks.db

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.kseniabl.cardstasks.db.dao.AddProdsDao
import com.kseniabl.cardstasks.db.dao.AllProdsDao
import com.kseniabl.cardstasks.db.dao.ChatDao
import com.kseniabl.cardstasks.db.db_models.AddProdsModel
import com.kseniabl.cardstasks.db.db_models.AllProdsModel
import com.kseniabl.cardstasks.db.db_models.ChatModel
import com.kseniabl.cardstasks.db.db_models.MapOfChatModels
import com.kseniabl.cardstasks.ui.base.CardChatModel
import com.kseniabl.cardstasks.ui.base.RetrofitApiHolder
import com.kseniabl.cardstasks.ui.models.CardModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.reactivestreams.Subscription
import retrofit2.Retrofit
import javax.inject.Inject

class ChatRepository @Inject constructor(val context: Context, val retrofit: Retrofit, val apiHolder: RetrofitApiHolder): RepositoryInterface {

    private var db = CardsTasksDatabase.getInstance(context)
    private var chatDao: ChatDao = db.chatDao()
    private var allProdsDao: AllProdsDao = db.allProdsDao()
    private val addProdsDao: AddProdsDao = db.addProdsDao()

    override fun insertAddProdsCardsList(list: List<CardModel>) {
        GlobalScope.launch {
            addProdsDao.insertAllCards(list)
        }
    }

    override fun insertAddProdCard(card: CardModel) {
        GlobalScope.launch {
            addProdsDao.insertOneCard(card)
        }
    }

    override fun getAddProdCards(): LiveData<List<CardModel>> {
        return addProdsDao.loadAllCards()
    }

    override fun deleteAddProdCard(card: CardModel) {
        GlobalScope.launch {
            addProdsDao.deleteCard(card)
        }
    }

    override fun deleteAddProdCardsList() {
        GlobalScope.launch {
            addProdsDao.deleteAllCards()
        }
    }

    fun getAllCards() = cashAllProds(
        query = {
            allProdsDao.loadAllCards()
        },
        fetch = {
            apiHolder.getAllCards()
        },
        saveFetchResult = { data ->
            data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : FlowableSubscriber<List<List<CardModel>>> {
                    override fun onSubscribe(s: Subscription) {
                        s.request(Long.MAX_VALUE)
                    }

                    override fun onNext(data: List<List<CardModel>>) {
                        GlobalScope.launch {
                            val list = convertListsToAllProdsModel(data)
                            allProdsDao.deleteAllCards()
                            allProdsDao.insertAllCards(list)
                        }
                    }

                    override fun onError(e: Throwable) {
                        Log.e("qqq", "error ${e.message}")
                    }

                    override fun onComplete() {
                    }

                })
        }
    )

    private fun convertListsToAllProdsModel(data: List<List<CardModel>>): MutableList<AllProdsModel> {
        val mutList = mutableListOf<AllProdsModel>()
        for (el in data) {
            mutList.add(AllProdsModel(cards = el.toMutableList()))
        }
        return mutList
    }

    fun getAllMsg(id: String): Single<MapOfChatModels> {
        return chatDao.loadAllMessages(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun insertMsg(msg: MapOfChatModels) {
        chatDao.insertMessage(msg)
    }

    fun deleteMsg(msg: MapOfChatModels) {
        chatDao.deleteMessage(msg)
    }

    fun loadReceivedMessages(id: String): Flowable<MapOfChatModels> {
        return chatDao.loadFlowableMessages(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadAllById(id: String): MapOfChatModels? {
        return chatDao.loadAllById(id)
    }

    fun loadAll(): List<MapOfChatModels> {
        return chatDao.loadAll()
    }

    fun setList(id: String, list: MutableList<CardChatModel>): Single<Int> {
        return chatDao.setList(id, list)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}