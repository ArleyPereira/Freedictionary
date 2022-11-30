package com.example.freedictionary.data.repository.remote

import com.example.freedictionary.data.remote.ServiceApi
import com.example.freedictionary.data.remote.model.WordDto
import com.example.freedictionary.domain.repository.remote.WordApiDataSource
import javax.inject.Inject

class WordApiDataSourceImpl @Inject constructor(
    private val serviceApi: ServiceApi
) : WordApiDataSource {

    override suspend fun getWord(word: String): List<WordDto> {
        return serviceApi.getWord(word)
    }

}