package com.example.codingchallenge.features.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codingchallenge.domain.usecase.GetUsersUseCase
import com.example.codingchallenge.data.model.User
import com.example.codingchallenge.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {

    private val _users = MutableLiveData<Result<List<User>>>()
    val users: LiveData<Result<List<User>>> = _users

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