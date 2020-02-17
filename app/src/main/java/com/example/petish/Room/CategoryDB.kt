package com.example.petish.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_db")
data class CategoryDB(
    @PrimaryKey
    @ColumnInfo
    val categoryId: String,
    @ColumnInfo val name: String,
    @ColumnInfo val img_url: String,
    @ColumnInfo val category_type: Int
)