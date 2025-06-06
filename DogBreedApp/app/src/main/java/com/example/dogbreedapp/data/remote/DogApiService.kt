package com.example.dogbreedapp.data.remote

import com.example.dogbreedapp.data.model.DogBreed
import retrofit2.http.GET
import retrofit2.http.Headers

interface DogApiService {

    @Headers("x-api-key: https://api.thedogapi.com/")
    @GET("v1/breeds")
    suspend fun getBreeds(): List<DogBreed>
}
