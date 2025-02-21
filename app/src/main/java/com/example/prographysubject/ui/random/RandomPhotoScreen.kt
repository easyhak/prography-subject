package com.example.prographysubject.ui.random

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.prographysubject.R
import com.example.prographysubject.ui.HomeBottomNavigation
import com.example.prographysubject.ui.NavigationItem

@Composable
fun RandomPhotoScreen(
    onPhotoMainClick: () -> Unit,
    onDetailClick: (String) -> Unit,
    viewModel: RandomViewModel = hiltViewModel()
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
