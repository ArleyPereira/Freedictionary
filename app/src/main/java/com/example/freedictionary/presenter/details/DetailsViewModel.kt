package com.example.freedictionary.presenter.details

import androidx.lifecycle.*
import com.example.freedictionary.data.remote.model.ErrorApi
import com.example.freedictionary.data.remote.model.toDomain
import com.example.freedictionary.domain.model.WordRefFirebase
import com.example.freedictionary.domain.model.WordSearchedDomain
import com.example.freedictionary.domain.usecase.firebase.GetFavoritesUseCase
import com.example.freedictionary.domain.usecase.firebase.RemoveFavoriteUseCase
import com.example.freedictionary.domain.usecase.firebase.SaveFavoriteUseCase
import com.example.freedictionary.domain.usecase.firebase.SaveWordHistoricUseCase
import com.example.freedictionary.domain.usecase.remote.GetWordApiUseCase
import com.example.freedictionary.domain.usecase.room.GetWordSearchedUseCase
import com.example.freedictionary.domain.usecase.room.InsertWordSearchedUseCase
import com.example.freedictionary.util.StateView
import com.example.freedictionary.util.getErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getWordUseCase: GetWordApiUseCase,
    private val getWordSearchedUseCase: GetWordSearchedUseCase,
    private val insertWordSearchedUseCase: InsertWordSearchedUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    private val saveWordHistoricUseCase: SaveWordHistoricUseCase
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

    fun getWordSearched(word: String) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val wordSearched = getWordSearchedUseCase(word)

            emit(StateView.Success(wordSearched))
        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(StateView.Error(null))
        }
    }

    fun getWordApi(word: String) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val result = getWordUseCase.invoke(word)

            emit(StateView.Success(result.map { it.toDomain() }))

        } catch (exception: HttpException) {
            val customError = exception.getErrorResponse<ErrorApi>()
            when (exception.code()) {
                404 -> {
                    emit(StateView.Error(customError?.message))
                }
                else -> {
                    emit(StateView.Error(customError?.message))
                }
            }
            exception.printStackTrace()
        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(StateView.Error(null))
        }
    }

    fun insertWordSearched(wordSearchedDomain: WordSearchedDomain) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val result = insertWordSearchedUseCase(wordSearchedDomain)

            emit(StateView.Success(result))

        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(StateView.Error(null))
        }
    }

    fun saveFavorite(wordRefFirebase: WordRefFirebase) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            saveFavoriteUseCase(wordRefFirebase)

            emit(StateView.Success(Unit))
        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(StateView.Error(null))
        }
    }

    fun removeFavorite(wordId: String) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            removeFavoriteUseCase(wordId)

            emit(StateView.Success(Unit))
        } catch (ex: Exception) {
            ex.printStackTrace()
            emit(StateView.Error(null))
        }
    }

    fun saveWordHistoric(wordRefFirebase: WordRefFirebase) = viewModelScope.launch {
        try {
            saveWordHistoricUseCase(wordRefFirebase)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

}