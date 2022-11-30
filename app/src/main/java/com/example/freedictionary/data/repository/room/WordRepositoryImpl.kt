package com.example.freedictionary.data.repository.room

import androidx.paging.PagingSource
import com.example.freedictionary.data.local.entity.WordEntity
import com.example.freedictionary.domain.repository.room.WordDbDataSource
import com.example.freedictionary.domain.repository.room.WordRepository
import com.example.freedictionary.presenter.paging.MainPagingSource
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(
    private val dbDataSource: WordDbDataSource
): WordRepository {

    override fun getWords(limit: Int, offset: Int): PagingSource<Int, WordEntity> {
        return MainPagingSource(dbDataSource)
    }

}