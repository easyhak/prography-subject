package com.example.prographysubject.ui.photo

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prographysubject.domain.usecase.GetPhotoDetailUseCase
import com.example.prographysubject.ui.photo.uistate.PhotoDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPhotoDetailUseCase: GetPhotoDetailUseCase
) : ViewModel() {
    private val photoId: String? = savedStateHandle.get<String>("id")

    private val _uiState = MutableStateFlow<PhotoDetailUiState>(PhotoDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        photoId?.let {
            Log.d("PhotoDetailViewModel", "photoId: $photoId")
            getPhotoDetail()
        }
    }

    private fun getPhotoDetail() {
        if (photoId != null) {
            viewModelScope.launch {
                try {
                    val detailPhoto = getPhotoDetailUseCase(photoId)
                    Log.d("PhotoDetailViewModel", "detailPhoto: $detailPhoto")
                    _uiState.value = PhotoDetailUiState.Success(detailPhoto)
                } catch (e: Exception) {
                    Log.e("PhotoDetailViewModel", "Error fetching photo detail", e)
                    _uiState.value = PhotoDetailUiState.Error(e.localizedMessage ?: "Unknown error")
                }
            }
        }
    }

}
