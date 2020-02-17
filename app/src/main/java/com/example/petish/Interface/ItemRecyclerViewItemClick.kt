package com.example.petish.Interface

import com.example.petish.Model.Item

interface ItemRecyclerViewItemClick {
    fun itemClicked(item: Item, position: Int);
}