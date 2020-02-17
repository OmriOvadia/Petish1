package com.example.petish.Model

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.processNextEventInCurrentThread

@Entity (tableName = "items")
class Item(
    @PrimaryKey
    @ColumnInfo
    var _id: String,
    @ColumnInfo
    var image: String,
    @ColumnInfo
    var description: String,
    @ColumnInfo
    var price: Int,
    @ColumnInfo
    var isFavorite: Boolean,
    @ColumnInfo
    var catId: String){
    override fun equals(other: Any?): Boolean {
        if(other is Item){
            return other._id == this._id
        }
        return false
    }
}

