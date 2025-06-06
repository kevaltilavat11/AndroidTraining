package com.example.dogbreedapp.data.repository

import com.example.dogbreedapp.data.model.DogBreed
import com.example.dogbreedapp.data.remote.DogApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DogRepository @Inject constructor(private val apiService: DogApiService) {

    fun getBreeds(): Flow<List<DogBreed>> = flow {
        val breeds = apiService.getBreeds()
        emit(breeds)
    }
}