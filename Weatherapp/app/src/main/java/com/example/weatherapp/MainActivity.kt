package com.example.weatherapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var adapter: CityAdapter
    private val cities = mutableListOf<City>()
    private lateinit var batteryPercent: TextView
    private lateinit var batteryIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        batteryPercent = findViewById(R.id.batteryPercent)
        batteryIcon = findViewById(R.id.batteryIcon)

        registerReceiver(batteryReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

        adapter = CityAdapter(cities, onCityClick = {
            val intent = Intent(this, CityActivity::class.java)
            intent.putExtra("lat", it.lat)
            intent.putExtra("lon", it.lon)
            startActivity(intent)
        }, onCityDelete = {
            cities.remove(it)
            adapter.notifyDataSetChanged()
        })

        val recyclerView = findViewById<RecyclerView>(R.id.cityList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapClickListener {
            val marker = MarkerOptions().position(it).title("Bookmarked Location")
            mMap.addMarker(marker)
            cities.add(City("Location", it.latitude, it.longitude))
            adapter.notifyDataSetChanged()
        }
    }

    private val batteryReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val level = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
            batteryPercent.text = "$level%"
            batteryIcon.setImageResource(
                when {
                    level >= 80 -> R.drawable.ic_battery_full
                    level >= 50 -> R.drawable.ic_battery_half
                    else -> R.drawable.ic_battery_low
                }
            )
        }
    }
}