package com.example.webanttrainee.presentation.di

import com.example.webanttrainee.data.remote.ResponseRepositoryImpl
import com.example.webanttrainee.domain.repository.ResponseRepository
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