package com.moracoding.firebaseexp.domain.repositories

import com.moracoding.firebaseexp.util.Resource
import kotlinx.coroutines.flow.Flow

interface FBMessagingRepository {
    fun getToken(): Flow<Resource<String>>
}