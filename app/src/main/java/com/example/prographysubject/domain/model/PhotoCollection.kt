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
) {
    companion object {
        fun mock(): PhotoCollection {
            return PhotoCollection(
                id = "1",
                description = "Photo Description",
                tags = listOf("tag1", "tag2", "tag3"),
                profileImage = "https://picsum.photos/200/300",
                username = "username",
                imageUrl = "https://picsum.photos/200/300",
                imageSize = ImageSize(200, 300),
                bookmarked = false
            )
        }
    }
}


data class ImageSize(
    val width: Int,
    val height: Int
)
