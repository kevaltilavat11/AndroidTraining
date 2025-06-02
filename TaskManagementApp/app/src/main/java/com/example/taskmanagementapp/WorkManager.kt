package com.example.taskmanagementapp

import android.content.Context
import androidx.work.*
import com.example.taskmanagementapp.data.Task
import java.util.concurrent.TimeUnit

class ReminderWorker(appContext: Context, params: WorkerParameters) : Worker(appContext, params) {
    override fun doWork(): Result {
        val taskTitle = inputData.getString("task_title") ?: return Result.failure()
        return Result.success()
    }
}

fun scheduleReminder(context: Context, task: Task) {
    val workRequest = OneTimeWorkRequestBuilder<ReminderWorker>()
        .setInitialDelay(task.dueDate - System.currentTimeMillis() - 10 * 60 * 1000, TimeUnit.MILLISECONDS)
        .setInputData(workDataOf("task_title" to task.title))
        .build()

    WorkManager.getInstance(context).enqueue(workRequest)
}