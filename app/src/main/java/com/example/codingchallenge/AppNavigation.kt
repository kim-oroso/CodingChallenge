package com.example.codingchallenge

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.codingchallenge.features.details.UserDetailScreen
import com.example.codingchallenge.features.list.UserListScreen

@Composable
fun AppNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "user_list") {
        composable("user_list") {
            UserListScreen(navController)
        }
        composable("user_detail/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: return@composable
            UserDetailScreen(userId)
        }
    }
}
