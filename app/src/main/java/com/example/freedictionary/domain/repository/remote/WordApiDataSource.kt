package com.example.freedictionary.domain.repository.remote

import com.example.freedictionary.data.remote.model.WordDto

interface WordApiDataSource {

    suspend fun getWord(word: String): List<WordDto>

}