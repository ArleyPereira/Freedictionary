package com.example.freedictionary.domain.model

import android.os.Parcelable
import com.example.freedictionary.data.remote.model.Phonetic
import kotlinx.parcelize.Parcelize

@Parcelize
data class WordsSearchedDomain(
    val meanings: List<MeaningDomain>? = null,
    val origin: String? = null,
    val phonetic: String? = null,
    val phonetics: List<Phonetic>? = null,
    val word: String? = null
) : Parcelable