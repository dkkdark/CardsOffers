package com.kseniabl.cardstasks.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [MapOfChatModels::class], version = 4)
@TypeConverters(ConverterEnum::class, ConvertersMutableList::class, ConvertersMutableListCardChatModel::class)
abstract class CardsTasksDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao

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