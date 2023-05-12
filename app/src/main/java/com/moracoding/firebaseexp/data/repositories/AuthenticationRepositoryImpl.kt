package com.moracoding.firebaseexp.data.repositories

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.moracoding.firebaseexp.domain.repositories.AuthenticationRepository
import com.moracoding.firebaseexp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(private val fbAuth: FirebaseAuth): AuthenticationRepository {

    override fun signIn(email: String, password: String): Flow<Resource<AuthResult>> = flow {
        emit(Resource.Loading())
        val result = fbAuth.signInWithEmailAndPassword(email, password).await()
        emit(Resource.Success(result))
    }.catch { emit(Resource.Error(message = it.message.toString())) }
        .flowOn(Dispatchers.IO)

    override fun signUp(email: String, password: String): Flow<Resource<AuthResult>> = flow {
        emit(Resource.Loading())
        val result = fbAuth.createUserWithEmailAndPassword(email, password).await()
        emit(Resource.Success(result))
    }.catch { emit(Resource.Error(message = it.message.toString())) }
        .flowOn(Dispatchers.IO)

    override fun getUser(): Flow<Resource<FirebaseUser?>> = flow {
        emit(Resource.Loading())
        val result = fbAuth.currentUser
        emit(Resource.Success(result))
    }.catch { emit(Resource.Error(message = it.message.toString())) }
        .flowOn(Dispatchers.IO)
}