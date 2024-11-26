package com.example.codingchallenge.features.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.codingchallenge.R
import com.example.codingchallenge.features.ErrorScreen
import com.example.codingchallenge.features.LoadingScreen
import com.example.codingchallenge.utils.Result

@Composable
fun UserListScreen(navController: NavController) {
    val userListViewModel: UserListViewModel = hiltViewModel()
    val users = userListViewModel.users.collectAsState(Result.Loading()).value

    when (users) {
        is Result.Loading<*> -> {
            LoadingScreen()
        }

        is Result.Success<*> -> {
            val userItems = users.data!!

            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(userItems) { user ->
                    UserItem(
                        user.avatar_url,
                        user.login,
                        user.type,
                        user.id,
                        user.node_id
                    ) {
                        // Navigate to user details
                        navController.navigate("user_detail/${user.login}")
                    }
                }
            }
        }

        is Result.Error<*> -> {
            ErrorScreen(message = "${users.message}")
        }
    }

}

@Composable
fun UserItem(
    avatarUrl: String,
    login: String,
    type: String,
    id: Int,
    nodeId: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(avatarUrl),
                contentDescription = login,
                modifier = Modifier
                    .size(140.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(CircleShape)
            )
            Text(
                text = String.format(stringResource(id = R.string.user_type), type),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = String.format(stringResource(id = R.string.user_id), id),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = String.format(stringResource(id = R.string.login_id), login),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = String.format(stringResource(id = R.string.node_id), nodeId),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Preview(showBackground = true)
@Composable
fun UserItemPreview() {
    UserItem("https://avatars.githubusercontent.com/u/1?v=4",
        "mojombo",
        "user",
        77,
        "SSDC283VSDF",
        onClick = {}
    )
}
