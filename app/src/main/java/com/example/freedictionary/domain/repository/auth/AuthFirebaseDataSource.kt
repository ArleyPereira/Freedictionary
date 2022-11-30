package com.example.freedictionary.domain.repository.auth

interface AuthFirebaseDataSource {

    suspend fun login(
        email: String,
        password: String
    )

    suspend fun register(
        email: String,
        password: String
    )

    suspend fun recover(
        email: String
    )

}