package com.example.foodapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.foodapp.data.local.FoodDao
import com.example.foodapp.data.model.Food

class FoodRepository(private val dao: FoodDao) {
    fun getPagedFood(): Pager<Int, Food> {
        return Pager(PagingConfig(pageSize = 10)) {
            dao.getAllFood()
        }
    }
}