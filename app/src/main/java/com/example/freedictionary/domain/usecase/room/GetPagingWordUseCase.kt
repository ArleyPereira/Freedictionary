package com.example.freedictionary.domain.usecase.room

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.freedictionary.data.local.entity.WordEntity
import com.example.freedictionary.domain.repository.room.WordRepository
import com.example.freedictionary.util.Constants.Paging.PAGING_INITIAL_LOAD_SIZE
import com.example.freedictionary.util.Constants.Paging.PAGING_LIMIT
import com.example.freedictionary.util.Constants.Paging.PAGING_OFFSET
import com.example.freedictionary.util.Constants.Paging.PAGING_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPagingWordUseCase @Inject constructor(
    private val repository: WordRepository
) {

    operator fun invoke(): Flow<PagingData<WordEntity>> {
        val pagingSource = repository.getWords(PAGING_LIMIT, PAGING_OFFSET)
        return Pager(
            config =
            PagingConfig(
                pageSize = PAGING_SIZE,
                enablePlaceholders = false,
                initialLoadSize = PAGING_INITIAL_LOAD_SIZE
            )
        ) {
            pagingSource
        }.flow
    }

}