package com.example.petish.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey
    @ColumnInfo
    val _id: String,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val image: String,
    @ColumnInfo
    var type: Int){
    override fun equals(other: Any?): Boolean {
        if( other is Category){
            if(other._id == _id && other.image == image && other.name == name) return true
        }
        return false
    }
}
