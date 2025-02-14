package com.example.prographysubject.domain.usecase

import androidx.paging.PagingData
import com.example.prographysubject.domain.model.PhotoCollection
import com.example.prographysubject.domain.repository.UnsplashRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCollectionsUseCase @Inject constructor(
    private val repository: UnsplashRepository
) {
    operator fun invoke(): Flow<PagingData<PhotoCollection>> {
        return repository.getCollections()
    }
}
