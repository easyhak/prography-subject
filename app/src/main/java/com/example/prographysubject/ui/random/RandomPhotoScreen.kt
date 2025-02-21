package com.example.prographysubject.ui.random

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.prographysubject.R
import com.example.prographysubject.domain.model.PhotoCollection
import com.example.prographysubject.ui.HomeBottomNavigation
import com.example.prographysubject.ui.NavigationItem

@Composable
fun RandomPhotoScreen(
    onPhotoMainClick: () -> Unit,
    onDetailClick: (String) -> Unit,
    viewModel: RandomPhotoViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    when (uiState) {
        RandomPhotoUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is RandomPhotoUiState.Success -> {
            RandomPhotoScreen(
                onPhotoMainClick = onPhotoMainClick,
                onDetailClick = onDetailClick,
                photo = (uiState as RandomPhotoUiState.Success).photo
            )
        }

        is RandomPhotoUiState.Error -> {
            // Error
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RandomPhotoScreen(
    onPhotoMainClick: () -> Unit,
    onDetailClick: (String) -> Unit,
    photo: PhotoCollection
) {

    val navigationItems = listOf(
        NavigationItem(
            icon = R.drawable.ic_house,
            isSelected = false,
            onClick = onPhotoMainClick,
        ),
        NavigationItem(
            icon = R.drawable.ic_cards,
            isSelected = true,
            onClick = {},
        ),
    )

    Scaffold(
        bottomBar = { HomeBottomNavigation(items = navigationItems) },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Prography Logo Image",
                        modifier = Modifier
                            .width(144.dp)
                            .height(24.dp)
                    )
                },
            )
        }
    ) { innerPadding ->
        RandomCardItem(
            photo = photo,
            onDetailClick = onDetailClick,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun RandomCardItem(
    photo: PhotoCollection = PhotoCollection.mock(),
    onDetailClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .weight(4f)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Black),
            contentAlignment = Alignment.Center,
        ) {
            val context = LocalContext.current
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(photo.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .aspectRatio(photo.imageSize.width.toFloat() / photo.imageSize.height.toFloat())
                    .fillMaxWidth()
            )
        }

        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Dislike",
                    tint = Color.Gray,
                    modifier = Modifier.size(52.dp)
                )
            }

            IconButton(
                onClick = { },
                modifier = Modifier
                    .background(Color(0xFFD81D45), shape = CircleShape)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Bookmark,
                    contentDescription = "Like",
                    tint = Color.White,
                    modifier = Modifier.size(72.dp)
                )
            }

            IconButton(onClick = { onDetailClick(photo.id) }) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Info",
                    tint = Color.Gray,
                    modifier = Modifier.size(52.dp)
                )
            }
        }
    }

}

//@Composable
//@Preview(showBackground = true)
//private fun CardItemPreview() {
//    RandomCardItem()
//}
//
//@Composable
//@Preview(showBackground = true)
//private fun RandomPhotoScreenPreview() {
//    RandomPhotoScreen(
//        onPhotoMainClick = {},
//        onDetailClick = {}
//    )
//}
