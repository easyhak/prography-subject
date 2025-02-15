package com.example.prographysubject.domain.usecase

import androidx.paging.PagingData
import com.example.prographysubject.data.repository.UnsplashRepository
import com.example.prographysubject.domain.model.PhotoCollection
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPagedPhotoCollectionsUseCase @Inject constructor(
    private val repository: UnsplashRepository
) {
    operator fun invoke(): Flow<PagingData<PhotoCollection>> {
        return repository.getPagedPhotoCollection()
    }
}
