package com.example.codingchallenge

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.codingchallenge.features.details.UserDetailScreen
import com.example.codingchallenge.features.list.UserListScreen

@Composable
fun AppNavigation(navController: NavHostController, navigationViewModel: NavigationViewModel = hiltViewModel()) {

    NavHost(navController = navController, startDestination = "user_list") {
        composable("user_list") {
            navigationViewModel.navigateTo(Screen.UserListView)
            UserListScreen(navController)
        }
        composable("user_detail/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: return@composable
            navigationViewModel.navigateTo(Screen.UserDetailView)
            UserDetailScreen(userId)
        }
    }
}
