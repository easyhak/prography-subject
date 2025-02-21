package com.example.prographysubject.ui.random

import androidx.lifecycle.ViewModel
import com.example.prographysubject.domain.usecase.GetRandomPhotoUseCase
import javax.inject.Inject

class RandomPhotoViewModel @Inject constructor(
    private val getRandomPhotoUseCase: GetRandomPhotoUseCase
) : ViewModel() {
    suspend fun getRandomPhoto() = getRandomPhotoUseCase()
}
