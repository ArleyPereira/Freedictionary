package com.example.freedictionary.presenter.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.freedictionary.data.local.entity.WordEntity
import com.example.freedictionary.domain.repository.room.WordDbDataSource
import kotlinx.coroutines.delay

class MainPagingSource(
    private val dbDataSource: WordDbDataSource
) : PagingSource<Int, WordEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WordEntity> {
        val page = params.key ?: 0

        return try {
            val entities = dbDataSource.getPagingWord(params.loadSize, page * params.loadSize)

            // simulate page loading
            if (page != 0) delay(1000)

            LoadResult.Page(
                data = entities,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (entities.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, WordEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}