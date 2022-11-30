package com.example.freedictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.freedictionary.domain.model.WordDomain
import com.example.freedictionary.util.Constants.Database.WORD_TABLE

@Entity(tableName = WORD_TABLE)
data class WordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val word: String? = null
)

fun WordEntity.toDomain(): WordDomain {
    return with(this) {
        WordDomain(
            id = this.id,
            word = this.word
        )
    }
}
