package com.moracoding.firebaseexp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.moracoding.firebaseexp.data.repositories.AuthenticationRepositoryImpl
import com.moracoding.firebaseexp.domain.repositories.AuthenticationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Singleton
    @Provides
    fun provideFBAuthRepository(fbAuth: FirebaseAuth): AuthenticationRepository = AuthenticationRepositoryImpl(fbAuth)
}