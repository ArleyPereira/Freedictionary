package com.example.freedictionary.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WordRefFirebase(
    var id: String = "",
    val idLocal: Long = 0,
    val word: String = ""
) : Parcelable