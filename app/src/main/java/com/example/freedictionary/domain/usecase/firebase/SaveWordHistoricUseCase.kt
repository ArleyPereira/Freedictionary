package com.example.freedictionary.domain.usecase.firebase

import com.example.freedictionary.domain.model.WordRefFirebase
import com.example.freedictionary.domain.repository.firebase.FirebaseRepository
import javax.inject.Inject

class SaveWordHistoricUseCase @Inject constructor(
    val repository: FirebaseRepository
) {

    suspend operator fun invoke(wordRefFirebase: WordRefFirebase) {
        repository.saveWordHistoric(wordRefFirebase)
    }

}