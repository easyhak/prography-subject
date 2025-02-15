package com.example.prographysubject.domain.model

data class PhotoCollection(
    val id: String,
    val description: String,
    val tags: List<String>,
    val profileImage: String,
    val username: String,
    val imageUrl: String,
    val imageSize: ImageSize,
    val bookmarked: Boolean
)


data class ImageSize(
    val width: Int,
    val height: Int
)
