package com.example.freedictionary.domain.repository.room

import androidx.paging.PagingSource
import com.example.freedictionary.data.local.entity.WordEntity

interface WordRepository {

    fun getWords(limit: Int, offset: Int): PagingSource<Int, WordEntity>

}