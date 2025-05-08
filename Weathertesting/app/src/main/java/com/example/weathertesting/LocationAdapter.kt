package com.example.weathertesting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LocationAdapter(
    private val locations: MutableList<Pair<Double, Double>>,
    private val onItemClick: (Pair<Double, Double>) -> Unit
) : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    inner class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val locationText: TextView = itemView.findViewById(R.id.tv_location)
        val btnDelete: Button = itemView.findViewById(R.id.btn_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_location, parent, false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val (lat, lon) = locations[position]
        holder.locationText.text = "Lat: $lat, Lon: $lon"
        holder.itemView.setOnClickListener {
            onItemClick(locations[position])
        }
        holder.btnDelete.setOnClickListener {
            locations.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, locations.size)
        }
    }

    override fun getItemCount(): Int = locations.size
}