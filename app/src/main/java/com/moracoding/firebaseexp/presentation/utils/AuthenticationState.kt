package com.moracoding.firebaseexp.presentation.utils

import com.google.firebase.firestore.DocumentReference
import com.moracoding.firebaseexp.domain.model.User

data class AuthenticationState(
    val isLoading: Boolean = false,
    val isSuccess: User = User(),
    val isError: String? = "",
    )

data class TransactionState(
    val isLoading: Boolean = false,
    val isSuccess: DocumentReference? = null,
    val isError: String? = "",
    )

data class FBMessageState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = "",
    )
