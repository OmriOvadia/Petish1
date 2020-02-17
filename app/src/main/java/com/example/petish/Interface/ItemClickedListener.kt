package com.example.petish.Interface

import com.example.petish.Model.Category
import com.example.petish.Model.Item

interface ItemClickedListener {
    fun itemClicked(category: Category);

}