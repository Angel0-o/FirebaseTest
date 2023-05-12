package com.moracoding.firebaseexp.domain.repositories

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.moracoding.firebaseexp.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    fun signIn(email:String, password:String) :Flow<Resource<AuthResult>>
    fun signUp(email:String, password:String) :Flow<Resource<AuthResult>>
    fun getUser() :Flow<Resource<FirebaseUser?>>
}