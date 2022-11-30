package com.example.freedictionary.domain.model

import android.os.Parcelable
import com.example.freedictionary.data.remote.model.Definition
import kotlinx.parcelize.Parcelize

@Parcelize
data class MeaningDomain(
    val wordSearchedId: Long? = null,
    val definitions: List<Definition>? = null,
    val partOfSpeech: String? = null
) : Parcelable

