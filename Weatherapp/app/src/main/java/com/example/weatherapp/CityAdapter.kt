package com.example.weatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CityAdapter(
    private val cities: List<City>,
    val onCityClick: (City) -> Unit,
    val onCityDelete: (City) -> Unit
) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]
        holder.name.text = city.name
        holder.itemView.setOnClickListener { onCityClick(city) }
        holder.delete.setOnClickListener { onCityDelete(city) }
    }

    override fun getItemCount() = cities.size

    class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.cityName)
        val delete: ImageButton = view.findViewById(R.id.deleteBtn)
    }
}