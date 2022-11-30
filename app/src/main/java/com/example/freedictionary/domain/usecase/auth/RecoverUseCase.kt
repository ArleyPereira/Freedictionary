package com.example.freedictionary.domain.usecase.auth

import com.example.freedictionary.data.repository.auth.AuthFirebaseDataSourceImpl
import javax.inject.Inject

class RecoverUseCase @Inject constructor(
    private val authFirebaseDataSourceImpl: AuthFirebaseDataSourceImpl
) {

    suspend operator fun invoke(email: String) {
        return authFirebaseDataSourceImpl.recover(email)
    }

}