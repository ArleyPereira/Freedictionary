package com.example.freedictionary.presenter.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freedictionary.domain.model.WordRefFirebase
import com.example.freedictionary.domain.usecase.firebase.GetHistoricsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoricViewModel @Inject constructor(
    private val getHistoricsUseCase: GetHistoricsUseCase,
) : ViewModel() {

    private val _historics = MutableLiveData<List<WordRefFirebase>>()
    val historics: LiveData<List<WordRefFirebase>>
        get() = _historics

    init {
        getHistorics()
    }

    private fun getHistorics() {
        viewModelScope.launch {
            val favorites = getHistoricsUseCase()
            favorites.collect {
                when {
                    it.isSuccess -> {
                        _historics.postValue(it.getOrNull())
                    }
                    it.isFailure -> {
                        _historics.postValue(emptyList())
                    }
                }
            }
        }
    }

}