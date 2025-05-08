package com.example.weathertesting

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherRepository {
    suspend fun getWeather(lat: Double, lon: Double, apiKey: String): WeatherResponse {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherApiService::class.java)
        return service.getCurrentWeather(lat, lon, apiKey, "metric")
    }
}