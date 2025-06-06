package com.example.dogbreedapp.ui.breedlist

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogbreedapp.databinding.ActivityBreedListBinding
import com.example.dogbreedapp.ui.adapter.BreedAdapter
import com.example.dogbreedapp.ui.breeddetail.BreedDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreedListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBreedListBinding
    private val viewModel: BreedListViewModel by viewModels()
    private lateinit var adapter: BreedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBreedListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = BreedAdapter(emptyList()) { breed ->
            val intent = Intent(this, BreedDetailActivity::class.java)
            intent.putExtra("breed_name", breed.name)
            startActivity(intent)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.setSearchQuery(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
        })

        viewModel.breeds.observe(this) { breeds ->
            adapter.updateData(breeds)
        }
    }
}