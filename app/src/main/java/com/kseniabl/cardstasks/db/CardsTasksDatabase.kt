package com.kseniabl.cardstasks.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kseniabl.cardstasks.db.dao.AddProdsDao
import com.kseniabl.cardstasks.db.dao.AllProdsDao
import com.kseniabl.cardstasks.db.dao.ChatDao
import com.kseniabl.cardstasks.db.db_models.AddProdsModel
import com.kseniabl.cardstasks.db.db_models.AllProdsModel
import com.kseniabl.cardstasks.db.db_models.MapOfChatModels
import com.kseniabl.cardstasks.ui.models.CardModel

@Database(entities = [MapOfChatModels::class, AllProdsModel::class, CardModel::class], version = 5)
@TypeConverters(ConvertersMutableListCardModel::class, ConvertersMutableListCardChatModel::class)
abstract class CardsTasksDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao
    abstract fun allProdsDao(): AllProdsDao
    abstract fun addProdsDao(): AddProdsDao

    companion object {
        private var instance: CardsTasksDatabase? = null

        fun getInstance(context: Context): CardsTasksDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    CardsTasksDatabase::class.java,
                    "CardsTasksDatabase")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance as CardsTasksDatabase
        }
    }
}