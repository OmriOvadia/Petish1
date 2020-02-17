package com.example.petish.Room.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.petish.Model.Category
import com.example.petish.Room.CategoryDB

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    fun getAll():List<Category>

    @Insert
    fun insertAll(users: List<Category>)

    @Delete
    fun delete(category: Category)
    @Query("SELECT * from categories WHERE type = (:type)")
    fun getCategoriesBYType(type: Int): List<Category>

    @Query("SELECT * FROM categories WHERE name LIKE :chars AND type = :type")
    fun searchCategories(chars: String, type: Int): List<Category>
}