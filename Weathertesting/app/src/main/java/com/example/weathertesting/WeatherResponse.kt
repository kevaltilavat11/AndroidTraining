package com.example.weathertesting

data class WeatherResponse(
    val main: Main,
    val wind: Wind
)

data class Main(val temp: Double, val humidity: Int)
data class Wind(val speed: Double)