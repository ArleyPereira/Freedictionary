package com.example.freedictionary.domain.repository.firebase

import com.example.freedictionary.domain.model.WordRefFirebase
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {

    suspend fun saveFavorite(wordRefFirebase: WordRefFirebase)

    suspend fun removeFavorite(wordId: String)

    fun getFavorites(): Flow<Result<List<WordRefFirebase>>>

    suspend fun saveWordHistoric(wordRefFirebase: WordRefFirebase)

    fun getHistorics(): Flow<Result<List<WordRefFirebase>>>

}