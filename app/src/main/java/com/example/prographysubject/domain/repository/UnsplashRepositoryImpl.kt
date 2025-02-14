package com.example.prographysubject.domain.repository

import androidx.paging.PagingData
import com.example.prographysubject.domain.model.PhotoCollection
import kotlinx.coroutines.flow.Flow

interface UnsplashRepository {
    fun getCollections(): Flow<PagingData<PhotoCollection>>
}
