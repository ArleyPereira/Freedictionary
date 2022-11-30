package com.example.freedictionary.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorApi(
    val message: String? = null
): Parcelable
