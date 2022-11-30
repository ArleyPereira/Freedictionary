package com.example.freedictionary.domain.usecase.firebase

import com.example.freedictionary.domain.model.WordRefFirebase
import com.example.freedictionary.domain.repository.firebase.FirebaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHistoricsUseCase @Inject constructor(
    val repository: FirebaseRepository
) {

    operator fun invoke(): Flow<Result<List<WordRefFirebase>>> {
        return repository.getHistorics()
    }

}