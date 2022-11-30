package com.example.freedictionary.data.remote.model

import android.os.Parcelable
import com.example.freedictionary.domain.model.MeaningDomain
import com.example.freedictionary.domain.model.WordSearchedDomain
import kotlinx.parcelize.Parcelize

@Parcelize
data class WordDto(
    val meanings: List<MeaningDomain>? = null,
    val origin: String? = null,
    val phonetic: String? = null,
    val phonetics: List<Phonetic>? = null,
    val word: String? = null
) : Parcelable

fun WordDto.toDomain(): WordSearchedDomain {
    return with(this) {
        WordSearchedDomain(
            word = this.word,
            origin = this.origin,
            phonetic = this.phonetic,
            phonetics = this.phonetics,
            meanings = this.meanings
        )
    }
}