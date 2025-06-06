package com.example.dogbreedapp.ui.breeddetail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.dogbreedapp.databinding.ActivityBreedDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBreedDetailBinding
    private val viewModel: BreedDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBreedDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val breedName = intent.getStringExtra("breed_name") ?: ""

        viewModel.loadBreed(breedName)

        viewModel.breed.observe(this) { breed ->
            if (breed != null) {
                binding.tvName.text = breed.name
                binding.tvTemperament.text = breed.temperament ?: "N/A"
                binding.tvLifeSpan.text = breed.life_span ?: "N/A"
                breed.image?.url?.let {
                    binding.ivBreedImage.load(it) {
                        placeholder(com.example.dogbreedapp.R.drawable.ic_dog_placeholder)
                    }
                }
            }
        }
    }
}