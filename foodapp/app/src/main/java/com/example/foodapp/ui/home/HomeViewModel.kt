package com.example.foodapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.foodapp.data.repository.FoodRepository

class HomeViewModel(private val repository: FoodRepository) : ViewModel() {
    val foodList = repository.getPagedFood().flow.cachedIn(viewModelScope)
}