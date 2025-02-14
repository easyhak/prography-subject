package com.example.prographysubject.ui.photo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.prographysubject.R
import com.example.prographysubject.domain.model.PhotoCollection
import com.example.prographysubject.ui.HomeBottomNavItem
import com.example.prographysubject.ui.HomeBottomNavigation
import com.example.prographysubject.ui.NavigationItem

@Composable
fun PhotoMainScreen(
    viewModel: CollectionViewModel = hiltViewModel()
) {

    val photoCollections = viewModel.photoCollections.collectAsLazyPagingItems()

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CommunityHomeScreen(
    onMapClick: () -> Unit,
    onDetailClick: (String, String) -> Unit,
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
            onClick = onMapClick,
        ),
    )

    Scaffold(
        bottomBar = {
            HomeBottomNavigation(items = navigationItems)
        },
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(bottom = 8.dp),
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Prography Logo Image",
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 4.dp)
                    )
                },
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(2.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),

            ) {
            items(
                count = photoCollections.itemCount,
                key = { index -> photoCollections[index]?.id ?: index }
            ) { index ->
//                val postItem = post[index]
//                if (postItem != null) {
//                    CommunityPostCard(
//                        post = postItem,
//                        onDetailClick = onDetailClick
//                    )
//                }
            }
        }
    }
}
