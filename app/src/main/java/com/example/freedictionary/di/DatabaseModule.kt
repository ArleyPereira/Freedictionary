package com.example.freedictionary.di

import android.content.Context
import androidx.room.Room
import com.example.freedictionary.R
import com.example.freedictionary.data.local.dao.WordDao
import com.example.freedictionary.data.local.dao.WordSearchedDao
import com.example.freedictionary.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        context.getString(R.string.app_name_database)
    ).createFromAsset("database/word.db").build()

    @Singleton
    @Provides
    fun provideWordDao(appDatabase: AppDatabase): WordDao = appDatabase.wordDao()

    @Singleton
    @Provides
    fun provideWordSearchedDao(appDatabase: AppDatabase): WordSearchedDao =
        appDatabase.wordSearchedDao()

}