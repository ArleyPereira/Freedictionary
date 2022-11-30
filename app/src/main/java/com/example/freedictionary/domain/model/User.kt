package com.example.freedictionary.domain.model

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String = "",
    val email: String = "",
    @get:Exclude
    val password: String = ""
): Parcelable
