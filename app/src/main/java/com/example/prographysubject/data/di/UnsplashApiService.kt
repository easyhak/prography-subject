package com.example.prographysubject.data.di

import com.example.prographysubject.data.dto.CollectionDto
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApiService {
    @GET("collections")
    suspend fun getCollections(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 10
    ): List<CollectionDto>
}
