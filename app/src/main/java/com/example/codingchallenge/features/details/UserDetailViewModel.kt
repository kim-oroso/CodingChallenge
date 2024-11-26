package com.example.codingchallenge.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codingchallenge.data.model.UserDetail
import com.example.codingchallenge.domain.usecase.GetUserDetailsUseCase
import com.example.codingchallenge.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserDetailsUseCase: GetUserDetailsUseCase
) : ViewModel() {

    private val _selectedUser = MutableStateFlow<Result<UserDetail>>(Result.Loading())
    val selectedUser: StateFlow<Result<UserDetail>> get() = _selectedUser

    // Fetch user details by ID
    fun fetchUserDetails(userId: String) {
        viewModelScope.launch {
            getUserDetailsUseCase(userId).collectLatest { user ->
                _selectedUser.value = user
            }
        }
    }
}