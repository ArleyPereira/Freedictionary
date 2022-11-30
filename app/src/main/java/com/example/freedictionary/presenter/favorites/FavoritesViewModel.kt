package com.example.freedictionary.presenter.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freedictionary.domain.model.WordRefFirebase
import com.example.freedictionary.domain.usecase.firebase.GetFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {

    private val _favorites = MutableLiveData<List<WordRefFirebase>>()
    val favorites: LiveData<List<WordRefFirebase>>
        get() = _favorites

    init {
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch {
            val favorites = getFavoritesUseCase()
            favorites.collect {
                when {
                    it.isSuccess -> {
                        _favorites.postValue(it.getOrNull())
                    }
                    it.isFailure -> {
                        _favorites.postValue(emptyList())
                    }
                }
            }
        }
    }

}