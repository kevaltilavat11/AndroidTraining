package com.example.studentmanagementdemo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String,
    var email: String,
    var gender: String,
    var department: String,
    var hobbies: String
)