package com.example.freedictionary.domain.model

import android.os.Parcelable
import com.example.freedictionary.data.remote.model.Phonetic
import kotlinx.parcelize.Parcelize

@Parcelize
data class WordSearchedDomain(
    val id: Long? = null,
    val word: String? = null,
    val phonetic: String? = null,
    val phonetics: List<Phonetic>? = null,
    val origin: String? = null,
    val meanings: List<MeaningDomain>? = null
) : Parcelable
