package com.example.prographysubject.domain.usecase

import com.example.prographysubject.data.repository.UnsplashRepository
import com.example.prographysubject.domain.model.PhotoCollection
import javax.inject.Inject

class GetPhotoDetailUseCase @Inject constructor(
    private val repository: UnsplashRepository
) {
    suspend operator fun invoke(photoId: String): PhotoCollection {
        return repository.getPhotoById(photoId)
    }
}
