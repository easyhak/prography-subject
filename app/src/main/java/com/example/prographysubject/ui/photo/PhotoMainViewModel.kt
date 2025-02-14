package com.example.prographysubject.ui.photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.prographysubject.domain.usecase.GetCollectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val getCollectionsUseCase: GetCollectionsUseCase
) : ViewModel() {
    val photoCollections = getCollectionsUseCase().cachedIn(viewModelScope)
}
