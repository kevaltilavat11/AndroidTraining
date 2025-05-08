package com.example.tracklocationapp
import com.example.tracklocationapp.network.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query
interface WeatherApiService {
    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = API_KEY
    ): WeatherResponse

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/"
        const val API_KEY   = "fae7190d7e6433ec3a45285ffcf55c86"
    }
}