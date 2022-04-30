package com.kseniabl.cardstasks.db.dao

import androidx.room.*
import com.kseniabl.cardstasks.db.db_models.AllProdsModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AllProdsDao {
    @Query("SELECT * FROM AllProdsModel")
    fun loadAllCards(): Flow<MutableList<AllProdsModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCards(card: MutableList<AllProdsModel>)

    @Query("DELETE FROM AllProdsModel")
    suspend fun deleteAllCards()
}