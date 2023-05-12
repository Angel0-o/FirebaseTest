package com.moracoding.firebaseexp.data.repositories

import com.google.firebase.messaging.FirebaseMessaging
import com.moracoding.firebaseexp.domain.repositories.FBMessagingRepository
import com.moracoding.firebaseexp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class FBMessagingRepositoryImpl : FBMessagingRepository {
    override fun getToken(): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        var result = FirebaseMessaging.getInstance().token.await()
        emit(Resource.Success(result))
    }.catch { emit(Resource.Error(message = it.message.toString())) }
        .flowOn(Dispatchers.IO)
}