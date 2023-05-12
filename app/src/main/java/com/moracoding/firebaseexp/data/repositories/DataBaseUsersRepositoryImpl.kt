package com.moracoding.firebaseexp.data.repositories

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.moracoding.firebaseexp.domain.model.User
import com.moracoding.firebaseexp.domain.repositories.DataBaseUsersRepository
import com.moracoding.firebaseexp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DataBaseUsersRepositoryImpl @Inject constructor(private val usersCollection: CollectionReference) : DataBaseUsersRepository {
    override fun createUser(user: User): Flow<Resource<DocumentReference>> = flow {
        emit(Resource.Loading())
        val result = usersCollection.add(user).await()
        emit(Resource.Success(result))
    }.catch { emit(Resource.Error(message = it.message.toString())) }
        .flowOn(Dispatchers.IO)

    override fun readUser(user: User): Flow<Resource<DocumentReference>> {
        TODO("Not yet implemented")
    }

    override fun updateUser(user: User): Flow<Resource<DocumentReference>> = flow {
        emit(Resource.Loading())
        val result = usersCollection.add(user).await()
        emit(Resource.Success(result))
    }.catch { emit(Resource.Error(message = it.message.toString())) }
        .flowOn(Dispatchers.IO)

    override fun deleteUser(user: User): Flow<Resource<DocumentReference>> {
        TODO("Not yet implemented")
    }
}