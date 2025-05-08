package com.example.tracklocationapp

import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LocationAdapter(
    private val locations: List<Pair<Double, Double>>,
    private val onRemove: (Int) -> Unit
) : RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val coords: TextView = view.findViewById(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = locations.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (lat, lon) = locations[position]
        holder.coords.text = "Lat: $lat, Lon: $lon"
        holder.itemView.setOnLongClickListener {
            onRemove(position)
            true
        }
    }
}