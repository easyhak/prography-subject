package com.example.prographysubject.ui.photo

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.prographysubject.R
import com.example.prographysubject.domain.model.PhotoCollection
import com.example.prographysubject.ui.HomeBottomNavItem
import com.example.prographysubject.ui.HomeBottomNavigation
import com.example.prographysubject.ui.NavigationItem
import com.example.prographysubject.ui.photo.components.PhotoCollectionItem

@Composable
fun PhotoMainScreen(
    onDetailClick: (String) -> Unit,
    viewModel: CollectionViewModel = hiltViewModel()
) {

    val photoCollections = viewModel.photoCollections.collectAsLazyPagingItems()
    CommunityHomeScreen(
        onDetailClick = onDetailClick,
        photoCollections = photoCollections
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CommunityHomeScreen(
    onDetailClick: (String) -> Unit,
    photoCollections: LazyPagingItems<PhotoCollection>
) {

    val navigationItems = listOf(
        NavigationItem(
            icon = HomeBottomNavItem.MyDream.icon,
            isSelected = true,
            onClick = {},
        ),
        NavigationItem(
            icon = HomeBottomNavItem.Community.icon,
            isSelected = false,
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

        LazyVerticalStaggeredGrid (
            columns = StaggeredGridCells.Adaptive(minSize = 150.dp),
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalItemSpacing = 10.dp
        ) {
            // todo book 마크 여부 확인
            item (span = StaggeredGridItemSpan.FullLine){
                Text(
                    text = "최신 사진",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().padding(10.dp)
                )
            }
            items(
                count = photoCollections.itemCount,
                key = { index -> photoCollections[index]?.id ?: index }
            ) { index ->
                val photoCollection = photoCollections[index]

                if (photoCollection != null) {
                    Log.d("PhotoMainScreen", "photoCollection: $photoCollection")
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

@Preview
@Composable
private fun CommunityHomeScreenPreview() {
//    CommunityHomeScreen(
//        onMapClick = {},
//        photoCollections = LazyPagingItems<PhotoCollection> { /* Mock Data 필요 */ }
//    )
}
