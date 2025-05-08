package com.example.weathertesting

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class CityFragment : Fragment() {

    private val apiKey = "fae7190d7e6433ec3a45285ffcf55c86"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val lat = arguments?.getDouble("lat") ?: 0.0
        val lon = arguments?.getDouble("lon") ?: 0.0

        val tvTemp = view.findViewById<TextView>(R.id.tv_temp)
        val tvHumidity = view.findViewById<TextView>(R.id.tv_humidity)
        val tvWind = view.findViewById<TextView>(R.id.tv_wind)
        val tvBattery = view.findViewById<TextView>(R.id.tv_battery)

        lifecycleScope.launch {
            val weather = WeatherRepository.getWeather(lat, lon, apiKey)
            tvTemp.text = "Temp: ${weather.main.temp}°C"
            tvHumidity.text = "Humidity: ${weather.main.humidity}%"
            tvWind.text = "Wind: ${weather.wind.speed} m/s"
        }

        val batteryPct = BatteryUtils.getBatteryPercentage(requireContext())
        tvBattery.text = "Battery: $batteryPct%"
    }
}
