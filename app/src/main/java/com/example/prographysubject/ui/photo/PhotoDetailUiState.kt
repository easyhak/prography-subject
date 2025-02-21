package com.example.prographysubject.ui.photo

import com.example.prographysubject.domain.model.PhotoCollection

sealed class PhotoDetailUiState {
    data object Loading : PhotoDetailUiState()
    data class Success(val photo: PhotoCollection) : PhotoDetailUiState()
    data class Error(val message: String) : PhotoDetailUiState()
}
