package com.example.dogbreedapp.ui.breeddetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogbreedapp.data.model.DogBreed
import com.example.dogbreedapp.data.repository.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedDetailViewModel @Inject constructor(
    private val repository: DogRepository
) : ViewModel() {

    private val _breed = MutableStateFlow<DogBreed?>(null)
    val breed: StateFlow<DogBreed?> = _breed

    fun loadBreed(name: String) {
        viewModelScope.launch {
            repository.getBreeds().collect { list ->
                _breed.value = list.find { it.name == name }
            }
        }
    }
}