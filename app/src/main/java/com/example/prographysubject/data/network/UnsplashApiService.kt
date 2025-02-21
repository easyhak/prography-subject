package com.example.prographysubject.data.network

import PhotoCollectionDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApiService {
    @GET("/photos")
    suspend fun getPagedPhotoCollections(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 10
    ): List<PhotoCollectionDto>

    @GET("photos/{id}")
    suspend fun getPhotoById(@Path("id") photoId: String): PhotoCollectionDto

    @GET("/photos/random")
    suspend fun getRandomPhoto(): PhotoCollectionDto
}
