package com.example.freedictionary.presenter.words

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.freedictionary.data.local.entity.toDomain
import com.example.freedictionary.domain.model.WordDomain
import com.example.freedictionary.domain.usecase.room.GetPagingWordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class WordsViewModel @Inject constructor(
    private val getPagingWordUseCase: GetPagingWordUseCase
) : ViewModel() {

    fun charactersPagingData(): Flow<PagingData<WordDomain>> {
        return getPagingWordUseCase().cachedIn(viewModelScope).map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }

}