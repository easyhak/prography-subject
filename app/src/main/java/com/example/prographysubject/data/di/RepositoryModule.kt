package com.example.prographysubject.data.di

import com.example.prographysubject.data.repository.UnsplashRepositoryImpl
import com.example.prographysubject.domain.repository.UnsplashRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUnsplashRepository(
        impl: UnsplashRepositoryImpl
    ): UnsplashRepository
}
