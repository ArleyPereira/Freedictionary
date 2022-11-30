package com.example.freedictionary.data.local.dao

import androidx.room.*
import com.example.freedictionary.data.local.entity.WordEntity

@Dao
interface WordDao {
    @Query("SELECT * FROM WORD_TABLE ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getPagingWord(limit: Int, offset: Int): List<WordEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWord(wordEntity: WordEntity): Long
}