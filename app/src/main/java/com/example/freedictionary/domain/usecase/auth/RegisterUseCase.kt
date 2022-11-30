package com.example.freedictionary.domain.usecase.auth

import com.example.freedictionary.data.repository.auth.AuthFirebaseDataSourceImpl
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authFirebaseDataSourceImpl: AuthFirebaseDataSourceImpl
) {

    suspend operator fun invoke(
        email: String,
        password: String
    ) {
        return authFirebaseDataSourceImpl.register(email, password)
    }

}