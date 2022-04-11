package com.kseniabl.cardstasks.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import com.kseniabl.cardstasks.ui.base.CardChatModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface ChatDao {
    @Query("SELECT * FROM MapOfChatModels WHERE userId = :id")
    fun loadAllMessages(id: String): Single<MapOfChatModels>

    @Query("SELECT * FROM MapOfChatModels WHERE userId = :id")
    fun loadFlowableMessages(id: String): Flowable<MapOfChatModels>

    @Query("UPDATE MapOfChatModels SET cardChatModelList = :list WHERE userId = :id")
    fun setList(id: String, list: MutableList<CardChatModel>): Single<Int>

    @Query("SELECT * FROM MapOfChatModels")
    fun loadAll(): List<MapOfChatModels>

    @Query("SELECT * FROM MapOfChatModels WHERE userId = :id")
    fun loadAllById(id: String): MapOfChatModels?

    @Insert(onConflict = REPLACE)
    fun insertMessage(message: MapOfChatModels)

    @Delete
    fun deleteMessage(message: MapOfChatModels)
}