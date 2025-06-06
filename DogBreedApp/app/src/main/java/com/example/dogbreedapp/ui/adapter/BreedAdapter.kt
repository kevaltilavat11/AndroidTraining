package com.example.dogbreedapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dogbreedapp.R
import com.example.dogbreedapp.data.model.DogBreed

class BreedAdapter(
    private var breeds: List<DogBreed>,
    private val onItemClick: (DogBreed) -> Unit
) : RecyclerView.Adapter<BreedAdapter.BreedViewHolder>() {

    inner class BreedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val breedName: TextView = itemView.findViewById(R.id.tvBreedName)
        val breedImage: ImageView = itemView.findViewById(R.id.ivBreedImage)

        fun bind(breed: DogBreed) {
            breedName.text = breed.name
            breed.image?.url?.let {
                breedImage.load(it) {
                    placeholder(R.drawable.ic_dog_placeholder)
                }
            } ?: breedImage.setImageResource(R.drawable.ic_dog_placeholder)

            itemView.setOnClickListener { onItemClick(breed) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_breed, parent, false)
        return BreedViewHolder(view)
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        holder.bind(breeds[position])
    }

    override fun getItemCount() = breeds.size

    fun updateData(newBreeds: List<DogBreed>) {
        breeds = newBreeds
        notifyDataSetChanged()
    }
}