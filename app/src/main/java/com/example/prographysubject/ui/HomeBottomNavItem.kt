package com.example.prographysubject.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SdCard
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class NavigationItem(
    val icon: ImageVector,
    val onClick: () -> Unit,
    val isSelected: Boolean,
)

@Composable
fun HomeBottomNavigation(items: List<NavigationItem>) {
    BottomAppBar (
        tonalElevation = 10.dp,
        containerColor = Color.White,
        contentColor = MaterialTheme.colorScheme.primary
    ) {

        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                    )
                },
                onClick = item.onClick,
                selected = item.isSelected,
            )
        }
    }
}


sealed class HomeBottomNavItem(
    val icon: ImageVector,
) {
    data object MyDream : HomeBottomNavItem(
        icon = Icons.Filled.Home,
    )

    data object Community : HomeBottomNavItem(
        icon = Icons.Filled.SdCard,
    )
}
