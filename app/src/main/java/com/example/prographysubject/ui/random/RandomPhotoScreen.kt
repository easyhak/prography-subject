package com.example.prographysubject.ui.random

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.prographysubject.R
import com.example.prographysubject.ui.HomeBottomNavigation
import com.example.prographysubject.ui.NavigationItem

@Composable
fun RandomPhotoScreen(
    onPhotoMainClick: () -> Unit,
    onDetailClick: (String) -> Unit,
    viewModel: RandomPhotoViewModel = hiltViewModel()
) {
    RandomPhotoScreen(
        onPhotoMainClick = onPhotoMainClick,
        onDetailClick = onDetailClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RandomPhotoScreen(
    onPhotoMainClick: () -> Unit,
    onDetailClick: (String) -> Unit
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
        Box(modifier = Modifier.padding(innerPadding)) {

        }
    }
}

@Composable
private fun RandomCardItem(modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .weight(4f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Black),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sample1),
                    contentDescription = "Profile Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth(),
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

                IconButton(onClick = {}) {
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
}

@Composable
@Preview(showBackground = true)
private fun CardItemPreview() {
    RandomCardItem()
}
