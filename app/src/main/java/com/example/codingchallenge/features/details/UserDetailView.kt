package com.example.codingchallenge.features.details

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.codingchallenge.NavigationViewModel
import com.example.codingchallenge.R
import com.example.codingchallenge.features.ErrorScreen
import com.example.codingchallenge.features.LoadingScreen
import com.example.codingchallenge.utils.Result

@Composable
fun UserDetailScreen(
    userId: String
) {
    val userDetailViewModel: UserDetailViewModel = hiltViewModel()
    val navigationViewModel = hiltViewModel<NavigationViewModel>()
    val selectedUser = userDetailViewModel.selectedUser.observeAsState(Result.Loading()).value

    LaunchedEffect(userId) {
        userDetailViewModel.fetchUserDetails(userId)
    }
    when (selectedUser) {
        is Result.Loading<*> -> {
            LoadingScreen()
        }

        is Result.Success<*> -> {
            val user = selectedUser.data
            user?.let {
                UserDetailItemScreen(
                    it.avatar_url,
                    user.name,
                    user.company,
                    user.location,
                    user.public_repos,
                    user.followers,
                    user.following
                )
            }
        }

        is Result.Error<*> -> {
            ErrorScreen(message = "${selectedUser.message}")
        }
    }
}

@Composable
fun UserDetailItemScreen(
    avatarUrl: String,
    name: String,
    company: String?,
    location: String?,
    publicRepos: Int?,
    followersCounter: Int?,
    followingCounter: Int?
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(avatarUrl),
                contentDescription = name,
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
            )
            Text(text = name, style = MaterialTheme.typography.titleLarge)

            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                IconTextRow(
                    R.drawable.ic_location,
                    R.string.location,
                    location
                )
                IconTextRow(
                    R.drawable.ic_company,
                    R.string.company,
                    company
                )
                IconTextRow(
                    R.drawable.ic_public_repo,
                    R.string.public_repos,
                    publicRepos?.toString()
                )
                IconTextRow(
                    R.drawable.ic_following,
                    R.string.following,
                    followingCounter?.toString()
                )
                IconTextRow(
                    R.drawable.ic_followers,
                    R.string.followers,
                    followersCounter?.toString()
                )
            }
        }
    }
}

@Composable
fun IconTextRow(
    @DrawableRes image: Int,
    @StringRes text: Int,
    value: String?
) {
    if (!value.isNullOrEmpty()) {
        Row {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = image),
                    contentDescription = stringResource(id = text)
                )
                Text(
                    text = stringResource(id = text),
                    style = MaterialTheme.typography.labelSmall
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserDetailItemPreview() {
    UserDetailItemScreen(
        "https://avatars.githubusercontent.com/u/1?v=4",
        "Jonh Doe",
        "@twitter @fb @insta",
        "California",
        51,
        300,
        250
    )
}

@Preview(showBackground = true)
@Composable
fun UserDetailItemNullPreview() {
    UserDetailItemScreen(
        "https://avatars.githubusercontent.com/u/1?v=4",
        "Jonh Doe",
        null,
        null,
        null,
        null,
        null
    )
}

@Preview(showBackground = true)
@Composable
fun IconTextRowPreview() {
    IconTextRow(
        R.drawable.ic_company,
        R.string.company,
        "@twitter @fb @insta"
    )
}
