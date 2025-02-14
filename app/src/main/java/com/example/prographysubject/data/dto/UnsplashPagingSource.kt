package com.example.prographysubject.data.dto

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.prographysubject.data.di.UnsplashApiService
import com.example.prographysubject.domain.model.PhotoCollection

class UnsplashPagingSource(
    private val api: UnsplashApiService
) : PagingSource<Int, PhotoCollection>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoCollection> {
        return try {
            val page = params.key ?: 1
            val response = api.getCollections(page)

            val data = response.map {
                PhotoCollection(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                    username = it.user.username,
                    location = it.user.location,
                    profileImage = it.user.profileImage.small,
                    imageUrl = it.coverPhoto?.urls?.raw ?: "",
                    bookmarked = false
                )
            }

            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PhotoCollection>): Int? {
        return null
    }
}
