package com.example.prographysubject.ui.photo.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.prographysubject.R
import com.example.prographysubject.domain.model.PhotoCollection


@Composable
fun PhotoCollectionItem(photo: PhotoCollection, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val isPreview = LocalInspectionMode.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(photo.imageSize.width.toFloat() / photo.imageSize.height.toFloat())
            .clip(RoundedCornerShape(10.dp))
    ) {
        if (isPreview) {
            Image(
                painter = painterResource(id = R.drawable.sample1),
                contentDescription = "Preview Image",
                modifier = Modifier.fillMaxSize()
            )
        } else {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(photo.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            text = photo.description,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp),
            maxLines = 2,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.surface
            )
        )
    }
}
