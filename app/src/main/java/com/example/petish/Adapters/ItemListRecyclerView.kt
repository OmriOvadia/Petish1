package com.example.petish.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.petish.APIRequests.SetFavoriteApiRequest

import com.example.petish.Model.ApiRequest
import com.example.petish.Model.Item
import com.example.petish.Interface.DataService
import com.example.petish.Interface.ItemClickedListener
import com.example.petish.Interface.ItemRecyclerViewItemClick
import com.example.petish.Network.RetrofitInstance
import com.example.petish.R
import com.example.petish.Utils.Constants
import com.moveosoftware.infrastructure.view.BaseRecyclerAdapter
import com.moveosoftware.infrastructure.view.BaseViewHolder
import com.nostra13.universalimageloader.core.ImageLoader
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.items_list_item.view.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemListRecyclerView(private val itemClickListener: ItemRecyclerViewItemClick ) :
    BaseRecyclerAdapter<ItemListRecyclerView.ViewHolder,Item>() {
    override fun getDataType(): Class<Item> = Item::class.java

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.items_list_item, parent, false)

        return ViewHolder(view,itemClickListener)
    }

//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.price.text = items[position].price.toString() + "$"
//        holder.desc.text = items[position].description
//        if (items[position].isFavorite) {
//            holder.favoriteIv.setImageResource(R.drawable.heart_fill)
//        } else {
//            holder.favoriteIv.setImageResource(R.drawable.heart_empty)
//        }
//        val imageLoader = ImageLoader.getInstance()
//        imageLoader.displayImage(items[position].image, holder.iv)
//        Log.d("recyclerviewAdapter", "onClick: " + items[position].isFavorite)
//        holder.favoriteIv.setOnClickListener {
//            Log.d("recyclerview","${items.get(position)}")
//            itemClickListener.itemClicked(items[position],position)
//
//        }
//    }



    inner class ViewHolder(itemView: View,private val itemClickListener: ItemRecyclerViewItemClick) : BaseViewHolder<Item>(itemView) {
        val price: TextView = itemView.item_recyclerview_price_tv

        val desc: TextView = itemView.item_recyclerview_desc_tv
        val iv: ImageView = itemView.item_recylerview_imageview
        val favoriteIv: ImageView = itemView.item_recyclerview_favorite_iv


        override fun bind(t: Item?, position: Int) {
            price.text = data[position].price.toString() + "$"
            desc.text = data[position].description
            if (data[position].isFavorite) {
                favoriteIv.setImageResource(R.drawable.heart_fill)
            } else {
                favoriteIv.setImageResource(R.drawable.heart_empty)
            }
            val imageLoader = ImageLoader.getInstance()
            imageLoader.displayImage(data[position].image, iv)
            Log.d("recyclerviewAdapter", "onClick: " + data[position].isFavorite)
            favoriteIv.setOnClickListener {
                Log.d("recyclerview","${data.get(position)}")
                itemClickListener.itemClicked(data[position],position)

            }
        }


    }


}
