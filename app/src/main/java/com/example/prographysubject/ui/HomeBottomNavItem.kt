package com.example.prographysubject.ui

import androidx.annotation.DrawableRes
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource

data class NavigationItem(
    @DrawableRes val icon: Int,
    val onClick: () -> Unit,
    val isSelected: Boolean,
)

@Composable
fun HomeBottomNavigation(items: List<NavigationItem>) {
    BottomAppBar (
        containerColor = Color(0xFF222222),
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = null,
                        tint = Color(0xFFFFFFFF),
                        modifier = Modifier.graphicsLayer(alpha = if (item.isSelected) 1f else 0.3f)
                    )
                },
                onClick = item.onClick,
                selected = false,
            )
        }
    }
}
