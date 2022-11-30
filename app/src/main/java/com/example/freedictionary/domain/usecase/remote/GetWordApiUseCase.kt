package com.example.freedictionary.domain.usecase.remote

import com.example.freedictionary.data.remote.model.WordDto
import com.example.freedictionary.domain.repository.remote.WordApiDataSource
import javax.inject.Inject

class GetWordApiUseCase @Inject constructor(
    private val repository: WordApiDataSource
) {

    suspend operator fun invoke(word: String): List<WordDto> {
        return repository.getWord(word)
    }

}