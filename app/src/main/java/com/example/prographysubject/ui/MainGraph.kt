package com.example.prographysubject.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.prographysubject.ui.photo.PhotoDetailScreen
import com.example.prographysubject.ui.photo.PhotoMainScreen
import kotlinx.serialization.Serializable

@Serializable
data object MainGraph {
    @Serializable
    data object PhotoMainRoute

    @Serializable
        data class PhotoDetailRoute(val id: String)

    @Serializable
    data class RandomDetailRoute(val id: String)
}

fun NavGraphBuilder.photoGraph(navController: NavController) {
    navigation<MainGraph>(
        startDestination = MainGraph.PhotoMainRoute,
    ) {
        composable<MainGraph.PhotoMainRoute> {
            PhotoMainScreen(
                onDetailClick = { photoId ->
                    navController.navigate(MainGraph.PhotoDetailRoute(photoId))
                },
            )
        }
        composable<MainGraph.PhotoDetailRoute> {
            PhotoDetailScreen()
        }

    }
}
