package com.moracoding.firebaseexp.di

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.moracoding.firebaseexp.data.repositories.DataBaseUsersRepositoryImpl
import com.moracoding.firebaseexp.data.repositories.FBMessagingRepositoryImpl
import com.moracoding.firebaseexp.domain.repositories.DataBaseUsersRepository
import com.moracoding.firebaseexp.domain.repositories.FBMessagingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideCollectionUsers(): CollectionReference = Firebase.firestore.collection("users")

    @Singleton
    @Provides
    fun provideUsersRepository(usersCollection: CollectionReference): DataBaseUsersRepository = DataBaseUsersRepositoryImpl(usersCollection)

    @Singleton
    @Provides
    fun provideFBMRepository(): FBMessagingRepository = FBMessagingRepositoryImpl()
}