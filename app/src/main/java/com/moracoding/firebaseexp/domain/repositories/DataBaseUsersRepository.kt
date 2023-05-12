package com.moracoding.firebaseexp.domain.repositories

import com.google.firebase.firestore.DocumentReference
import com.moracoding.firebaseexp.domain.model.User
import com.moracoding.firebaseexp.util.Resource
import kotlinx.coroutines.flow.Flow

interface DataBaseUsersRepository {
    fun createUser(user: User): Flow<Resource<DocumentReference>>
    fun readUser(user: User): Flow<Resource<DocumentReference>>
    fun updateUser(user: User): Flow<Resource<DocumentReference>>
    fun deleteUser(user: User): Flow<Resource<DocumentReference>>
}