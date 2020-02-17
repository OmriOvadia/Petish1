package com.example.petish.Room.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.petish.Model.Item

@Dao
interface ItemDao {
    @Query("SELECT * FROM items")
    fun getAllItems():List<Item>
    @Insert
    fun insertAll(items: ArrayList<Item>)
    @Delete
    fun deleteItempFromDB(item: Item)
    @Query("SELECT * FROM items WHERE catId = :catId")
    fun getItemFromDbByCatId(catId: String):List<Item>
    @Query ("SELECT * FROM items WHERE isFavorite = 1")
    fun getAllFavoriteItems():LiveData<List<Item>>
    @Update
    fun updateItem(item: Item)
}