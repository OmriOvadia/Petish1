package com.example.petish.Room.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.petish.Model.Category
import com.example.petish.Model.Item
import com.example.petish.Room.CategoryDB
import com.example.petish.Room.DAO.CategoryDao
import com.example.petish.Room.DAO.ItemDao

@Database(entities = [Category::class, Item::class],version = 2, exportSchema = false)
abstract class AppDataBase:RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun ItemDao(): ItemDao

}