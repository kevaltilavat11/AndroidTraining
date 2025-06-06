package com.example.dogbreedapp.ui.breedlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogbreedapp.data.model.DogBreed
import com.example.dogbreedapp.data.repository.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreedListViewModel @Inject constructor(
    private val repository: DogRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")

    // Expose search query as flow to update UI
    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    val breeds: StateFlow<List<DogBreed>> = _searchQuery
        .debounce(300)
        .flatMapLatest { query ->
            repository.getBreeds()
                .map { list ->
                    if (query.isEmpty()) list
                    else list.filter { it.name.contains(query, ignoreCase = true) }
                }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}