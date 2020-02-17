package com.example.petish.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.petish.Model.Item
import com.example.petish.R
import com.moveosoftware.infrastructure.view.BaseRecyclerAdapter
import com.moveosoftware.infrastructure.view.BaseViewHolder
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.favorite_recycler_view_item.view.*

class FavoriteRecyclerViewAdapter() :
    BaseRecyclerAdapter<FavoriteRecyclerViewAdapter.ViewHolder,Item>() {
    override fun getDataType(): Class<Item> = Item::class.java

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.favorite_recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(itemView: View) : BaseViewHolder<Item>(itemView) {
        val price: TextView = itemView.item_recyclerview_favorite_price_tv
        val desc: TextView = itemView.item_recyclerview_favorite_desc_tv
        val iv: ImageView = itemView.favorite_fragment_recyclerview_iv
        override fun bind(t: Item?, position: Int) {
            desc.text = data[position].description
            price.text = data[position].price.toString() + "$"
            val imageLoader = ImageLoader.getInstance()
            imageLoader.displayImage(data[position].image, iv)
        }




    }






}
