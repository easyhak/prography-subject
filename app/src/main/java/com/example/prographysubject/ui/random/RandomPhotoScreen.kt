package com.example.prographysubject.ui.random

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.rememberSwipeableState
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.prographysubject.R
import com.example.prographysubject.domain.model.PhotoCollection
import com.example.prographysubject.ui.HomeBottomNavigation
import com.example.prographysubject.ui.NavigationItem
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun RandomPhotoScreen(
    onPhotoMainClick: () -> Unit,
    onDetailClick: (String) -> Unit,
    viewModel: RandomPhotoViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    RandomPhotoScreen(
        onPhotoMainClick = onPhotoMainClick,
        onDetailClick = onDetailClick,
        onSwipeNext = viewModel::getRandomPhoto,
        uiState = uiState
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RandomPhotoScreen(
    onPhotoMainClick: () -> Unit,
    onDetailClick: (String) -> Unit,
    onSwipeNext: () -> Unit,
    uiState: RandomPhotoUiState
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
            uiState = uiState,
            onDetailClick = onDetailClick,
            onSwipeNext = onSwipeNext,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun RandomCardItem(
    uiState: RandomPhotoUiState,
    onDetailClick: (String) -> Unit,
    onSwipeNext: () -> Unit,
    modifier: Modifier = Modifier
) {


    when (uiState) {
        is RandomPhotoUiState.Loading -> {
            RandomCardItemContent(
                onDetailClick = onDetailClick,
                onSwipeNext = onSwipeNext,
                photo = null,
                modifier = modifier
            )
        }

        is RandomPhotoUiState.Success -> {
            RandomCardItemContent(
                onDetailClick = onDetailClick,
                onSwipeNext = onSwipeNext,
                photo = uiState.photo,
                modifier = modifier
            )
        }

        is RandomPhotoUiState.Error -> {
            // 로딩 실패
        }
    }

}

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
private fun RandomCardItemContent(
    onDetailClick: (String) -> Unit,
    onSwipeNext: () -> Unit,
    photo: PhotoCollection?,
    modifier: Modifier = Modifier
) {

    val scope = rememberCoroutineScope()
    val swipeableState = rememberSwipeableState(initialValue = 0)
    val offsetX = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }
    val density = LocalDensity.current

    val swipeThreshold = with(density) { 150.dp.toPx() }

    LaunchedEffect(photo) {
        offsetX.snapTo(0f)
        offsetY.snapTo(0f)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .weight(4f)
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Black)
                .fillMaxSize()
                .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        scope.launch {
                            offsetX.snapTo(offsetX.value + delta)
                        }
                    },
                    onDragStopped = {
                        scope.launch {
                            if (offsetX.value > swipeThreshold) {
                                offsetX.animateTo(500f, tween(300))
                                onSwipeNext()
                            } else if (offsetX.value < -swipeThreshold) {
                                offsetX.animateTo(-500f, tween(300))
                                onSwipeNext()
                            } else {
                                offsetX.animateTo(0f, spring())
                            }
                        }
                    },
                ),
            contentAlignment = Alignment.Center,
        ) {
            val context = LocalContext.current
            if (photo == null) {
                CircularProgressIndicator(
                    modifier = Modifier.size(72.dp),
                    color = Color.Gray
                )
            } else {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(photo.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(photo.imageSize.width.toFloat() / photo.imageSize.height.toFloat())
                )
            }
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

            IconButton(onClick = {
                if (photo != null) {
                    onDetailClick(photo.id)
                }
            }) {
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
