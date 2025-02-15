package com.example.prographysubject.ui.photo

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    viewModel: CollectionViewModel = hiltViewModel()
) {

    val photoCollections = viewModel.photoCollections.collectAsLazyPagingItems()
    CommunityHomeScreen(
        photoCollections = photoCollections
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CommunityHomeScreen(
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
            items(
                count = photoCollections.itemCount,
                key = { index -> photoCollections[index]?.id ?: index }
            ) { index ->
                val photoCollection = photoCollections[index]

                if (photoCollection != null) {
                    Log.d("PhotoMainScreen", "photoCollection: $photoCollection")
                    PhotoCollectionItem(
                        photo = photoCollection,
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
