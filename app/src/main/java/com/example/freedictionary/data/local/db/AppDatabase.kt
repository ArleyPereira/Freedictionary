package com.example.freedictionary.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.freedictionary.R
import com.example.freedictionary.data.local.dao.WordDao
import com.example.freedictionary.data.local.dao.WordSearchedDao
import com.example.freedictionary.data.local.entity.*

@Database(
    entities = [
        WordEntity::class,
        WordsSearchedEntity::class,
        DefinitionEntity::class,
        MeaningEntity::class,
        PhoneticEntity::class
    ], version = 1, exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao
    abstract fun wordSearchedDao(): WordSearchedDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    context.getString(R.string.app_name_database)
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}