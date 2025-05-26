package com.example.foodapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodapp.data.model.Food

@Dao
interface FoodDao {
    @Query("SELECT * FROM food_table ORDER BY id ASC")
    fun getAllFood(): PagingSource<Int, Food>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(foodList: List<Food>)
}