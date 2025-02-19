package com.example.prographysubject.di

import android.content.Context
import com.example.prographysubject.data.datastore.BookmarkDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideBookmarkDataStore(@ApplicationContext context: Context): BookmarkDataStore {
        return BookmarkDataStore(context)
    }
}
