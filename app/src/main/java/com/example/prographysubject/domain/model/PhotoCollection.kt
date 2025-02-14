package com.example.prographysubject.domain.model

data class PhotoCollection(
    val id: String,
    val title: String,
    val description: String?,
    val location: String?,
    val profileImage: String,
    val username: String,
    val imageUrl: String,
    val bookmarked: Boolean
)
