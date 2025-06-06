package com.example.dogbreedapp.di

import com.example.dogbreedapp.data.remote.ApiClient
import com.example.dogbreedapp.data.remote.DogApiService
import com.example.dogbreedapp.data.repository.DogRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDogApiService(): DogApiService = ApiClient.apiService

    @Provides
    @Singleton
    fun provideDogRepository(apiService: DogApiService): DogRepository = DogRepository(apiService)
}