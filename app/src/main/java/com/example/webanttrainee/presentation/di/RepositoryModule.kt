package com.example.webanttrainee.presentation.di

import com.example.data.remote.ResponseRepositoryImpl
import com.example.domain.repository.ResponseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindResponseRepository(
        responseRepositoryImpl: ResponseRepositoryImpl
    ): ResponseRepository
}