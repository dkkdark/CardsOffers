package com.kseniabl.cardstasks.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kseniabl.cardstasks.db.db_models.AddProdsModel
import com.kseniabl.cardstasks.ui.models.CardModel
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

@Dao
interface AddProdsDao {
    @Query("SELECT * FROM CardModel")
    fun loadAllCards(): LiveData<List<CardModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCards(cards: List<CardModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOneCard(card: CardModel)

    @Query("DELETE FROM CardModel")
    fun deleteAllCards()

    @Delete
    fun deleteCard(card: CardModel)
}