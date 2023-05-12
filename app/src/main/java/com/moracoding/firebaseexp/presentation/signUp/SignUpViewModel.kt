package com.moracoding.firebaseexp.presentation.signUp

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
class SignUpViewModel @Inject constructor(private val repository: AuthenticationRepository) :
    ViewModel() {

    val _signUpState = Channel<AuthenticationState>()
    val signUpState = _signUpState.receiveAsFlow()

    fun signUp(email: String, password: String) = viewModelScope.launch {
        repository.signUp(email, password).collect { result ->
            when (result) {
                is Resource.Success -> _signUpState.send(
                    AuthenticationState(
                        isSuccess = User(
                            email = result.data?.user?.email,
                            tsLogIn = System.currentTimeMillis().toFormattedDateString()
                        )
                    )
                )
                is Resource.Loading -> _signUpState.send(AuthenticationState(isLoading = true))
                is Resource.Error -> _signUpState.send(AuthenticationState(isError = result.message))
            }
        }
    }
}