package com.example.freedictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.freedictionary.util.Constants.Database.WORDS_SEARCHED_TABLE

@Entity(tableName = WORDS_SEARCHED_TABLE)
data class WordsSearchedEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val origin: String?,
    val phonetic: String?,
    val word: String?
)