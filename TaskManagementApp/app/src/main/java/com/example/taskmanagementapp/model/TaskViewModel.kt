package com.example.taskmanagementapp.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.taskmanagementapp.Repository.TaskRepository
import com.example.taskmanagementapp.data.Task
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TaskRepository
    val allTasks: LiveData<PagingData<Task>>

    init {
        val dao = AppDatabase.getDatabase(application).taskDao()
        repository = TaskRepository(dao)
        allTasks = repository.allTasks.cachedIn(viewModelScope).asLiveData()
    }

    fun insert(task: Task) = viewModelScope.launch { repository.insert(task) }
    fun update(task: Task) = viewModelScope.launch { repository.update(task) }
    fun delete(task: Task) = viewModelScope.launch { repository.delete(task) }
    suspend fun getTaskById(id: Int) = repository.getTaskById(id)
}