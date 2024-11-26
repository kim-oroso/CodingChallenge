package com.example.codingchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.codingchallenge.features.AppTopAppBar
import com.example.codingchallenge.ui.theme.CodingChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CodingChallengeTheme {
                val navController = rememberNavController()
                val navigationViewModel = hiltViewModel<NavigationViewModel>()
                val currentScreen by navigationViewModel.currentScreen.collectAsState()

                Scaffold(
                    topBar = {
                        AppTopAppBar(
                            title = currentScreen.title,
                            showBackButton = currentScreen != Screen.UserListView,
                            onBackClick = { navigationViewModel.popBack(navController) }
                        )
                    },
                    content = {
                        Surface(
                            modifier = Modifier
                                .padding(it),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            AppNavigation(navController, navigationViewModel)
                        }
                    }
                )

            }

        }
    }
}

