package com.example.prographysubject.ui.random

import com.example.prographysubject.domain.model.PhotoCollection

sealed class RandomPhotoUiState {
    data object Loading : RandomPhotoUiState()
    data class Success(val photo: PhotoCollection) : RandomPhotoUiState()
    data class Error(val message: String) : RandomPhotoUiState()
}
