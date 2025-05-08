package com.example.weathertesting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val locations = mutableListOf<Pair<Double, Double>>() // lat, lon

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.recycler_locations)
        val btnAdd = view.findViewById<Button>(R.id.btn_add_location)

        val adapter = LocationAdapter(locations) {
            val bundle = Bundle().apply {
                putDouble("lat", it.first)
                putDouble("lon", it.second)
            }
            findNavController().navigate(R.id.action_homeFragment_to_cityFragment, bundle)
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        btnAdd.setOnClickListener {
            locations.add(Pair(37.7749, -122.4194))
            adapter.notifyDataSetChanged()
        }

        return view
    }
}
