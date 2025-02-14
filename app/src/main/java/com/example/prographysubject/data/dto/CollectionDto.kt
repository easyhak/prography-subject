package com.example.prographysubject.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionDto(
    val id: String,
    val title: String,
    val description: String?,
    @SerialName("cover_photo") val coverPhoto: CoverPhotoDto?,
    val user: UserDto
)

@Serializable
data class CoverPhotoDto(
    val id: String,
    val urls: UrlsDto
)

@Serializable
data class UrlsDto(
    val raw: String
)

@Serializable
data class UserDto(
    val username: String,
    val location: String?,
    @SerialName("profile_image") val profileImage: ProfileImageDto
)

@Serializable
data class ProfileImageDto(
    val small: String
)
