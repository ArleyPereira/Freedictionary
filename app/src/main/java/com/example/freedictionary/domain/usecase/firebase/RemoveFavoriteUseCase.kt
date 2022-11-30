package com.example.freedictionary.domain.usecase.firebase

import com.example.freedictionary.domain.repository.firebase.FirebaseRepository
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(
    val repository: FirebaseRepository
) {

    suspend operator fun invoke(wordId: String) {
        repository.removeFavorite(wordId)
    }

}