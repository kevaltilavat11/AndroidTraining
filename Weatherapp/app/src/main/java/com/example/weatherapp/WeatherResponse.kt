package com.example.weatherapp

data class WeatherResponse(
    val main: Main,
    val wind: Wind,
    val rain: Rain?
)

data class Main(val temp: Double, val humidity: Int)
data class Wind(val speed: Double)
data class Rain(val `1h`: Double?)