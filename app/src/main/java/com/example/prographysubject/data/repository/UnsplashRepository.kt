package com.example.prographysubject.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.prographysubject.data.network.UnsplashApiService
import com.example.prographysubject.data.dto.UnsplashPagingSource
import com.example.prographysubject.domain.model.PhotoCollection
import kotlinx.coroutines.flow.Flow
import toDomain
import javax.inject.Inject

class UnsplashRepository @Inject constructor(
    private val apiService: UnsplashApiService
) {
    fun getPagedPhotoCollection(): Flow<PagingData<PhotoCollection>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = { UnsplashPagingSource(unsplashApiService = apiService) }
        ).flow
    }

    suspend fun getPhotoById(photoId: String): PhotoCollection {
        return apiService.getPhotoById(photoId).toDomain()
    }
}
