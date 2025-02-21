package com.example.prographysubject.ui.photo

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.prographysubject.R
import com.example.prographysubject.domain.model.PhotoCollection
import com.example.prographysubject.ui.HomeBottomNavigation
import com.example.prographysubject.ui.NavigationItem
import com.example.prographysubject.ui.photo.components.BookmarkCollectionItem
import com.example.prographysubject.ui.photo.components.PhotoCollectionItem
import com.example.prographysubject.ui.photo.components.ShimmerEffect

@Composable
fun PhotoMainScreen(
    onRandomPhotoClick: () -> Unit,
    onDetailClick: (String) -> Unit,
    viewModel: PhotoMainViewModel = hiltViewModel()
) {

    val photoCollections = viewModel.photoCollections.collectAsLazyPagingItems()
    val bookmarkedPhotos by viewModel.bookmarkedPhotos.collectAsStateWithLifecycle()
    // todo uiState 사용하기
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    CommunityHomeScreen(
        onRandomPhotoClick = onRandomPhotoClick,
        onDetailClick = onDetailClick,
        photoCollections = photoCollections,
        bookmarkedPhotos = bookmarkedPhotos,
        isLoading = isLoading
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CommunityHomeScreen(
    onRandomPhotoClick: () -> Unit,
    onDetailClick: (String) -> Unit,
    photoCollections: LazyPagingItems<PhotoCollection>,
    bookmarkedPhotos: List<PhotoCollection>,
    isLoading: Boolean
) {

    val navigationItems = listOf(
        NavigationItem(
            icon = R.drawable.ic_house,
            isSelected = true,
            onClick = {},
        ),
        NavigationItem(
            icon = R.drawable.ic_cards,
            isSelected = false,
            onClick = onRandomPhotoClick,
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

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(minSize = 150.dp),
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalItemSpacing = 10.dp
        ) {
            // 중복 많은건 나중에 리팩토링하기
            // Shimmer Effect
            if (isLoading && bookmarkedPhotos.isEmpty()) {
                item(span = StaggeredGridItemSpan.FullLine) {
                    Column {
                        Text(
                            text = "북마크한 사진",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            repeat(4) {
                                ShimmerEffect(
                                    modifier = Modifier
                                        .size(120.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                )
                            }
                        }
                    }
                }
            }
            // 아이템 보여주기
            else if (!isLoading && bookmarkedPhotos.isNotEmpty()) {
                item(span = StaggeredGridItemSpan.FullLine) {
                    Column {
                        Text(
                            text = "북마크한 사진",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 10.dp).padding(bottom = 8.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            bookmarkedPhotos.forEach { photo ->
                                BookmarkCollectionItem(
                                    photo = photo,
                                    onClick = { onDetailClick(photo.id) }
                                )
                            }
                        }
                    }
                }
            }

            item(span = StaggeredGridItemSpan.FullLine) {
                Text(
                    text = "최신 사진",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }
            // photoCollections.itemCount = 0 이면 shimmer를 보여주기
            if (photoCollections.itemCount == 0) {
                repeat(8) {
                    item {
                        ShimmerEffect(
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                        )
                    }
                }
            } else {
                items(
                    count = if (photoCollections.itemCount > 0) photoCollections.itemCount else 6, // 데이터 없으면 6개 shimmer
                    key = { index -> photoCollections.peek(index)?.id ?: index }
                ) { index ->
                    val photoCollection = photoCollections[index]
                    if (photoCollection != null) {
                        PhotoCollectionItem(
                            photo = photoCollection,
                            modifier = Modifier.clickable {
                                onDetailClick(photoCollection.id)
                            }
                        )
                    }

                }
            }
        }
    }
}

@Preview
@Composable
private fun CommunityHomeScreenPreview() {
//    CommunityHomeScreen(
//        onMapClick = {},
//        photoCollections = LazyPagingItems<PhotoCollection> { /* Mock Data 필요 */ }
//    )
}
