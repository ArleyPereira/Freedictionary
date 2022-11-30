package com.example.freedictionary.data.remote.model

import android.os.Parcelable
import com.example.freedictionary.data.local.entity.PhoneticEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Phonetic(
    val wordSearchedId: Long,
    val audio: String? = null,
    val text: String? = null
) : Parcelable

fun Phonetic.toEntity(): PhoneticEntity {
    return with(this) {
        PhoneticEntity(
            wordSearchedId = this.wordSearchedId,
            audio = this.audio,
            text = this.text
        )
    }
}