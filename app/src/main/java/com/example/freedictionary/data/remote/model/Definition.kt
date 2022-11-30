package com.example.freedictionary.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Definition(
    val definition: String? = null,
    val example: String? = null
): Parcelable