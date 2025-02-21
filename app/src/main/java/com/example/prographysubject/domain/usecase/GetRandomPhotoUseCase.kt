package com.example.prographysubject.domain.usecase

import com.example.prographysubject.data.repository.UnsplashRepository
import com.example.prographysubject.domain.model.PhotoCollection
import javax.inject.Inject

class GetRandomPhotoUseCase @Inject constructor(
    private val repository: UnsplashRepository
) {
    suspend operator fun invoke(): PhotoCollection {
        return repository.getRandomPhoto()
    }
}
