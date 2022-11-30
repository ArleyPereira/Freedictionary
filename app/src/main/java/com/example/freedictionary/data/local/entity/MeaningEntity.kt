package com.example.freedictionary.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.freedictionary.util.Constants.Database.MEANING_TABLE

@Entity(tableName = MEANING_TABLE)
data class MeaningEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "word_searched_id")
    val wordSearchedId: Long,

    @ColumnInfo(name = "part_of_speech")
    val partOfSpeech: String?
)