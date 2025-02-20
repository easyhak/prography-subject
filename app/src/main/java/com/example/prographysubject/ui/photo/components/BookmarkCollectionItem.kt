package com.example.prographysubject.ui.photo.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.prographysubject.domain.model.PhotoCollection

@Composable
fun BookmarkCollectionItem(
    photo: PhotoCollection,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = photo.imageUrl,
        contentDescription = "Bookmarked Photo",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .height(128.dp)
            .aspectRatio(photo.imageSize.width.toFloat() / photo.imageSize.height.toFloat())
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
    )
}
