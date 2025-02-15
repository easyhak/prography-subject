package com.example.prographysubject.data.dto

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.prographysubject.data.di.UnsplashApiService
import com.example.prographysubject.domain.model.PhotoCollection
import toDomain

class UnsplashPagingSource(
    private val unsplashApiService: UnsplashApiService
) : PagingSource<Int, PhotoCollection>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoCollection> {
        return try {
            val page = params.key ?: 1

            val response = unsplashApiService.getPagedPhotoCollections(page)
            // unsplash api 가 이상함
            val data = if (page == 1) {
                // 첫 번째 페이지는 10개 항목만
                response.take(10)
            } else {
                // 첫 번째 항목 제외하고 10개 항목 가져오기
                response.drop(1).take(10)
            }.map { it.toDomain() }

            Log.d("UnsplashPagingSource", "load: $data")

            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            Log.d("UnsplashPagingSource", "load: $e")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PhotoCollection>): Int? {
        return state.anchorPosition
    }
}
