package com.example.foodapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodapp.data.model.Food

@Database(entities = [Food::class], version = 1)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
}