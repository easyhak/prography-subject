package com.example.prographysubject.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainGraph,
    ) {
        photoGraph(navController = navController)
    }
}
