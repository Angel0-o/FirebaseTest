package com.moracoding.firebaseexp.presentation.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moracoding.firebaseexp.domain.model.User
import com.moracoding.firebaseexp.domain.repositories.AuthenticationRepository
import com.moracoding.firebaseexp.presentation.utils.AuthenticationState
import com.moracoding.firebaseexp.presentation.utils.toFormattedDateString
import com.moracoding.firebaseexp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val repository: AuthenticationRepository) :
    ViewModel() {

    val _signInState = Channel<AuthenticationState>()
    val signInState = _signInState.receiveAsFlow()

    fun signIn(email: String, password: String) = viewModelScope.launch {
        repository.signIn(email, password).collect { result ->
            when (result) {
                is Resource.Success -> _signInState.send(
                    AuthenticationState(
                        isSuccess = User(
                            email = result.data?.user?.email,
                            tsLogIn = System.currentTimeMillis().toFormattedDateString()
                        )
                    )
                )
                is Resource.Loading -> _signInState.send(AuthenticationState(isLoading = true))
                is Resource.Error -> _signInState.send(AuthenticationState(isError = result.message))
            }
        }
    }
}