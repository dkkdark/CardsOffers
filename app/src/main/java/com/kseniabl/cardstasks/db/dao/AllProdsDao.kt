package com.kseniabl.cardstasks.db.dao

import androidx.room.*
import com.kseniabl.cardstasks.db.db_models.AllProdsModel
import com.kseniabl.cardstasks.ui.models.CardModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AllProdsDao {
    @Query("SELECT * FROM AllProdsModel")
    fun loadAllCardsFlow(): Flow<MutableList<AllProdsModel>>

    @Query("SELECT * FROM AllProdsModel")
    fun loadAllCards(): List<AllProdsModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCards(card: MutableList<AllProdsModel>)

    @Query("DELETE FROM AllProdsModel")
    suspend fun deleteAllCards()
}