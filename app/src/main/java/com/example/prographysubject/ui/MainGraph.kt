package com.example.prographysubject.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.example.prographysubject.ui.photo.PhotoDetailScreen
import com.example.prographysubject.ui.photo.PhotoMainScreen
import com.example.prographysubject.ui.random.RandomPhotoScreen
import kotlinx.serialization.Serializable

@Serializable
data object MainGraph {
    @Serializable
    data object PhotoMainRoute

    @Serializable
    data class PhotoDetailRoute(val id: String)

    @Serializable
    data object RandomPhotoRoute
}

fun NavGraphBuilder.photoGraph(navController: NavController) {
    navigation<MainGraph>(
        startDestination = MainGraph.PhotoMainRoute,
    ) {
        composable<MainGraph.PhotoMainRoute> {
            PhotoMainScreen(
                onRandomPhotoClick = {
                    navController.navigate(MainGraph.RandomPhotoRoute, navOptions = navOptions {
                        popUpTo(MainGraph.PhotoMainRoute) { inclusive = true }
                    })
                },
                onDetailClick = { photoId ->
                    navController.navigate(MainGraph.PhotoDetailRoute(photoId))
                },
            )
        }
        composable<MainGraph.PhotoDetailRoute> {
            PhotoDetailScreen()
        }

        composable<MainGraph.RandomPhotoRoute> {
            RandomPhotoScreen(
                onPhotoMainClick = {
                    navController.navigate(MainGraph.PhotoMainRoute, navOptions = navOptions {
                        popUpTo(MainGraph.RandomPhotoRoute) { inclusive = true }
                    })
                },
                onDetailClick = { photoId ->
                    navController.navigate(MainGraph.PhotoDetailRoute(photoId))
                },
            )
        }

    }
}
