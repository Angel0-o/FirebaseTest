package com.moracoding.firebaseexp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moracoding.firebaseexp.domain.model.User
import com.moracoding.firebaseexp.domain.repositories.DataBaseUsersRepository
import com.moracoding.firebaseexp.domain.repositories.FBMessagingRepository
import com.moracoding.firebaseexp.presentation.utils.AuthenticationState
import com.moracoding.firebaseexp.presentation.utils.FBMessageState
import com.moracoding.firebaseexp.presentation.utils.TransactionState
import com.moracoding.firebaseexp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: DataBaseUsersRepository,
    private val fBMRepository: FBMessagingRepository
) : ViewModel() {

    val _createUserState = Channel<TransactionState>()
    val createUserState = _createUserState.receiveAsFlow()

    val _tokenState = Channel<FBMessageState>()
    val tokenState = _tokenState.receiveAsFlow()

    fun createUser(user: User) = viewModelScope.launch {
        repository.createUser(user).collect { result ->
            when (result) {
                is Resource.Success -> _createUserState.send(TransactionState(isSuccess = result.data))
                is Resource.Loading -> _createUserState.send(TransactionState(isLoading = true))
                is Resource.Error -> _createUserState.send(TransactionState(isError = result.message))
            }
        }
    }

    fun getFBToken() = viewModelScope.launch {
        fBMRepository.getToken().collect { result ->
            when (result) {
                is Resource.Success -> _tokenState.send(FBMessageState(isSuccess = result.data))
                is Resource.Loading -> _tokenState.send(FBMessageState(isLoading = true))
                is Resource.Error -> _tokenState.send(FBMessageState(isError = result.message))
            }
        }
    }

}