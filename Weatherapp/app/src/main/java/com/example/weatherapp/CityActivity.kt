package com.example.weatherapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        val lat = intent.getDoubleExtra("lat", 0.0)
        val lon = intent.getDoubleExtra("lon", 0.0)

        val temperature = findViewById<TextView>(R.id.temperature)
        val humidity = findViewById<TextView>(R.id.humidity)
        val wind = findViewById<TextView>(R.id.wind)
        val rain = findViewById<TextView>(R.id.rain)

        val apiService = WeatherService.api
        apiService.getCurrentWeather(lat, lon, "fae7190d7e6433ec3a45285ffcf55c86")
            .enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    val weather = response.body()
                    weather?.let {
                        temperature.text = "Temp: ${it.main.temp}°C"
                        humidity.text = "Humidity: ${it.main.humidity}%"
                        wind.text = "Wind: ${it.wind.speed} m/s"
                        rain.text = "Rain: ${it.rain?.`1h` ?: 0.0} mm"
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {}
            })
    }
}