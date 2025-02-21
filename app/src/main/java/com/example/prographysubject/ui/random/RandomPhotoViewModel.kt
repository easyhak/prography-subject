package com.example.prographysubject.ui.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prographysubject.domain.usecase.GetRandomPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomPhotoViewModel @Inject constructor(
    private val getRandomPhotoUseCase: GetRandomPhotoUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<RandomPhotoUiState>(RandomPhotoUiState.Loading)
    val uiState: StateFlow<RandomPhotoUiState> = _uiState.asStateFlow()

    init {
        getRandomPhoto()
    }

    fun getRandomPhoto() {
        viewModelScope.launch {
            _uiState.value = RandomPhotoUiState.Loading
            try {
                val photoUrl = getRandomPhotoUseCase()
                _uiState.value = RandomPhotoUiState.Success(photoUrl)
            } catch (e: Exception) {
                _uiState.value = RandomPhotoUiState.Error("Failed to load image. Please try again.")
            }
        }
    }
}
