package com.kseniabl.cardstasks.db

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface ChatDao {
    @Query("SELECT * FROM MapOfChatModels")
    fun loadAllMessages(): Single<MapOfChatModels>

    @Query("UPDATE MapOfChatModels SET chatModelList = :list WHERE userId = :id")
    fun setList(id: String, list: MutableList<ChatModel>)

    @Query("SELECT * FROM MapOfChatModels")
    fun loadAll(): MapOfChatModels

    @Query("SELECT * FROM MapOfChatModels WHERE userId = :id")
    fun loadAllById(id: String): MapOfChatModels?

    @Insert(onConflict = REPLACE)
    fun insertMessage(message: MapOfChatModels)

    @Delete
    fun deleteMessage(message: MapOfChatModels)
}