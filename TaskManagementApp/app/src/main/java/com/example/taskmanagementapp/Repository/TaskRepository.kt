package com.example.taskmanagementapp.Repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.taskmanagementapp.data.Task
import com.example.taskmanagementapp.data.TaskDao

class TaskRepository(private val dao: TaskDao) {
    val allTasks = Pager(PagingConfig(pageSize = 10)) { dao.getAllTasks() }.flow

    suspend fun insert(task: Task) = dao.insert(task)
    suspend fun update(task: Task) = dao.update(task)
    suspend fun delete(task: Task) = dao.delete(task)
    suspend fun getTaskById(id: Int) = dao.getTaskById(id)
}