package com.example.prographysubject.ui.photo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.prographysubject.domain.model.ImageSize
import com.example.prographysubject.domain.model.PhotoCollection

@Composable
fun PhotoDetailScreen(
    viewModel: PhotoDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        is PhotoDetailUiState.Loading -> {
            // todo shimmer 구현
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is PhotoDetailUiState.Error -> {
            Text((uiState as PhotoDetailUiState.Error).message)
        }

        is PhotoDetailUiState.Success -> {
            val photoDetail = (uiState as PhotoDetailUiState.Success).photo
            PhotoDetailScreen(
                photoDetail = photoDetail,
                onBookmarkClick = viewModel::toggleBookmark,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PhotoDetailScreen(
    photoDetail: PhotoCollection,
    onBookmarkClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(photoDetail.username) },
                navigationIcon = {
                    val context = LocalContext.current
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(photoDetail.profileImage)
                            .crossfade(true)
                            .build(),
                        contentDescription = "User Profile Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(36.dp)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(24.dp))
                    )
                },
                actions = {
                    IconButton(onClick = {/*Todo */ }) {
                        Icon(
                            imageVector = Icons.Filled.Download,
                            contentDescription = "Download",
                        )
                    }
                    IconButton(onClick = onBookmarkClick) {
                        Icon(
                            imageVector = Icons.Filled.Bookmark,
                            contentDescription = "Bookmark",
                            modifier = Modifier.graphicsLayer(alpha = if (photoDetail.bookmarked) 1f else 0.3f)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.Bottom
        ) {
            Spacer(modifier = Modifier.weight(1f))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(photoDetail.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(photoDetail.imageSize.width.toFloat() / photoDetail.imageSize.height.toFloat())
                    .padding(horizontal = 12.dp)
                    .clip(RoundedCornerShape(15.dp))
            )
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Text(
                    text = photoDetail.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = photoDetail.tags.joinToString(" #", prefix = "#"),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPhotoDetailScreen() {
    PhotoDetailScreen(
        photoDetail = PhotoCollection(
            id = "1",
            username = "username",
            imageUrl = "https://picsum.photos/200/300",
            imageSize = ImageSize(200, 300),
            description = "description",
            tags = listOf("tag1", "tag2"),
            profileImage = "",
            bookmarked = false,
        ),
        onBookmarkClick = {}
    )
}
