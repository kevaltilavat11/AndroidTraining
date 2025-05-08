package com.example.tracklocationapp.network

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("main")    val main: Main
)

data class Weather(
    @SerializedName("id")          val id: Int,
    @SerializedName("main")        val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon")        val icon: String
)

data class Main(
    @SerializedName("temp")      val temp: Double,
    @SerializedName("feels_like")val feelsLike: Double,
    @SerializedName("temp_min")  val tempMin: Double,
    @SerializedName("temp_max")  val tempMax: Double,
    @SerializedName("pressure")  val pressure: Int,
    @SerializedName("humidity")  val humidity: Int
)