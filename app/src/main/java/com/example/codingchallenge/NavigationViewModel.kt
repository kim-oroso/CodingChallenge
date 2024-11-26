package com.example.codingchallenge

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel() {
    private val _currentScreen = MutableStateFlow<Screen>(Screen.UserListView)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    fun navigateTo(screen: Screen) {
        _currentScreen.value = screen
    }

    fun popBack(navController: NavHostController) {
        navController.popBackStack()
    }
}

sealed class Screen(val title: String) {
    data object UserListView : Screen("User List")
    data object UserDetailView : Screen("Details")
}