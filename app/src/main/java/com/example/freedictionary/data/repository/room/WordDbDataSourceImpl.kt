package com.example.freedictionary.data.repository.room

import com.example.freedictionary.data.local.dao.WordDao
import com.example.freedictionary.data.local.entity.WordEntity
import com.example.freedictionary.domain.repository.room.WordDbDataSource
import javax.inject.Inject

class WordDbDataSourceImpl @Inject constructor(
    private val wordDAO: WordDao
) : WordDbDataSource {

    override suspend fun getPagingWord(limit: Int, offset: Int): List<WordEntity> {
        return wordDAO.getPagingWord(limit, offset)
    }

}