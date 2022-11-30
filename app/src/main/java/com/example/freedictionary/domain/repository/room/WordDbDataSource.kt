package com.example.freedictionary.domain.repository.room

import com.example.freedictionary.data.local.entity.*

interface WordDbDataSource {

    suspend fun getPagingWord(limit: Int, offset: Int): List<WordEntity>

}