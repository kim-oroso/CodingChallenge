package com.example.codingchallenge.features.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codingchallenge.data.model.User
import com.example.codingchallenge.domain.usecase.GetUsersUseCase
import com.example.codingchallenge.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {

    // hot flow real time loading
    private val _users = MutableStateFlow<Result<List<User>>>(Result.Loading())
    val users: StateFlow<Result<List<User>>> get() = _users

    init {
        fetchUsers()
    }

    // Fetch user list
    private fun fetchUsers() {
        viewModelScope.launch {
            getUsersUseCase().collectLatest { userList ->
                _users.value = userList
            }
        }
    }

}