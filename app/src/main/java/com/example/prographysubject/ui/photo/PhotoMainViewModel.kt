package com.example.prographysubject.ui.photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.prographysubject.data.datastore.BookmarkDataStore
import com.example.prographysubject.domain.model.PhotoCollection
import com.example.prographysubject.domain.usecase.GetPagedPhotoCollectionsUseCase
import com.example.prographysubject.domain.usecase.GetPhotoDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoMainViewModel @Inject constructor(
    private val getPagedPhotoCollectionsUseCase: GetPagedPhotoCollectionsUseCase,
    private val getPhotoDetailUseCase: GetPhotoDetailUseCase,
    private val bookmarkDataStore: BookmarkDataStore
) : ViewModel() {
    val photoCollections = getPagedPhotoCollectionsUseCase().cachedIn(viewModelScope)

    private val _bookmarkedPhotos = MutableStateFlow<List<PhotoCollection>>(emptyList())
    val bookmarkedPhotos = _bookmarkedPhotos.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        loadBookmarkedPhotos()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun loadBookmarkedPhotos() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                bookmarkDataStore.getBookmarkedPhotos()
                    .flatMapLatest { ids ->
                        flow {
                            val photos = ids.mapNotNull { id ->
                                runCatching { getPhotoDetailUseCase(id) }.getOrNull()
                            }
                            emit(photos)
                        }
                    }
                    .collectLatest { bookmarkedPhotos ->
                        _bookmarkedPhotos.value = bookmarkedPhotos
                        _isLoading.value = false
                    }
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }
}
