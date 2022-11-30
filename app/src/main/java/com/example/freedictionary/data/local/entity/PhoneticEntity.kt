package com.example.freedictionary.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.freedictionary.data.remote.model.Phonetic
import com.example.freedictionary.util.Constants.Database.PHONETIC_TABLE

@Entity(tableName = PHONETIC_TABLE)
data class PhoneticEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "word_searched_id")
    val wordSearchedId: Long,

    val audio: String?,
    val text: String?
)

fun PhoneticEntity.toDomain(): Phonetic {
    return with(this) {
        Phonetic(
            wordSearchedId = this.wordSearchedId,
            audio = this.audio,
            text = this.text
        )
    }
}