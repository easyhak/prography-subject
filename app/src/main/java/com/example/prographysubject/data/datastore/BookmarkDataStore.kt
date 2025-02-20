package com.example.prographysubject.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookmarkDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataStore = context.dataStore

    companion object {
        private val BOOKMARKED_PHOTOS = stringSetPreferencesKey("bookmarked_photos")

        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "bookmark_prefs")
    }

    suspend fun saveBookmark(photoId: String) {
        dataStore.edit { preferences ->
            val currentBookmarks = preferences[BOOKMARKED_PHOTOS] ?: emptySet()
            preferences[BOOKMARKED_PHOTOS] = currentBookmarks + photoId
        }
    }

    suspend fun removeBookmark(photoId: String) {
        dataStore.edit { preferences ->
            val currentBookmarks = preferences[BOOKMARKED_PHOTOS] ?: emptySet()
            preferences[BOOKMARKED_PHOTOS] = currentBookmarks - photoId
        }
    }

    fun isBookmarked(photoId: String): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            val bookmarks = preferences[BOOKMARKED_PHOTOS] ?: emptySet()
            photoId in bookmarks
        }
    }

    fun getBookmarkedPhotos(): Flow<Set<String>> {
        return dataStore.data.map { preferences ->
            preferences[BOOKMARKED_PHOTOS] ?: emptySet()
        }
    }
}
