package com.example.freedictionary.presenter.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.freedictionary.domain.usecase.auth.RegisterUseCase
import com.example.freedictionary.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    fun register(email: String, password: String) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            registerUseCase(email, password)

            emit(StateView.Success(Unit))

        } catch (ex: Exception) {
            emit(StateView.Error(ex.message))
        }
    }

}