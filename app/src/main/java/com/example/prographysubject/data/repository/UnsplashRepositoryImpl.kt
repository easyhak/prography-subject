package com.example.prographysubject.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.prographysubject.data.dto.UnsplashPagingSource
import com.example.prographysubject.data.di.UnsplashApiService
import com.example.prographysubject.domain.model.PhotoCollection
import com.example.prographysubject.domain.repository.UnsplashRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UnsplashRepositoryImpl @Inject constructor(
    private val api: UnsplashApiService
) : UnsplashRepository {
    override fun getCollections(): Flow<PagingData<PhotoCollection>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UnsplashPagingSource(api) }
        ).flow
    }
}
