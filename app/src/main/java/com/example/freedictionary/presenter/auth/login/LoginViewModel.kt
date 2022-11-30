package com.example.freedictionary.presenter.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.freedictionary.domain.usecase.auth.LoginUseCase
import com.example.freedictionary.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    fun login(email: String, password: String) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            loginUseCase(email, password)

            emit(StateView.Success(null))

        } catch (ex: Exception) {
            emit(StateView.Error(ex.message))
        }
    }

}