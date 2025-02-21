package com.example.prographysubject.ui.photo

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prographysubject.data.datastore.BookmarkDataStore
import com.example.prographysubject.domain.usecase.GetPhotoDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPhotoDetailUseCase: GetPhotoDetailUseCase,
    private val bookmarkDataStore: BookmarkDataStore,
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
                    _uiState.value = PhotoDetailUiState.Success(detailPhoto)
                    observeBookmarkState()
                } catch (e: Exception) {
                    _uiState.value = PhotoDetailUiState.Error(e.localizedMessage ?: "Unknown error")
                }
            }
        }
    }

    private fun observeBookmarkState() {
        if (photoId != null) {
            viewModelScope.launch {
                bookmarkDataStore.isBookmarked(photoId).collect { bookmarked ->
                    val currentState = _uiState.value
                    if (currentState is PhotoDetailUiState.Success) {
                        _uiState.value = currentState.copy(photo = currentState.photo.copy(bookmarked = bookmarked))
                    }
                }
            }
        }
    }

    fun toggleBookmark() {
        photoId?.let {
            viewModelScope.launch {
                val currentState = _uiState.value
                if (currentState is PhotoDetailUiState.Success) {
                    val newBookmarkState = !currentState.photo.bookmarked
                    if (newBookmarkState) {
                        bookmarkDataStore.saveBookmark(it)
                    } else {
                        bookmarkDataStore.removeBookmark(it)
                    }
                    _uiState.value = currentState.copy(photo = currentState.photo.copy(bookmarked = newBookmarkState))
                }
            }
        }
    }
}
