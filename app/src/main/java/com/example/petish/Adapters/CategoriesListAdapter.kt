package com.example.petish.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.petish.Interface.ItemClickedListener
import com.example.petish.Model.Category
import com.example.petish.R
import com.moveosoftware.infrastructure.view.BaseRecyclerAdapter
import com.moveosoftware.infrastructure.view.BaseViewHolder
import com.nostra13.universalimageloader.core.ImageLoader
import kotlinx.android.synthetic.main.categories_recyclerview_item.view.*

class CategoriesListAdapter(
    private val itemClickedListener:ItemClickedListener
) : BaseRecyclerAdapter<CategoriesListAdapter.MyViewHolder,Category>() {
    override fun getDataType(): Class<Category> = Category::class.java

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.categories_recyclerview_item, parent, false)
//        return ViewHolder(view)
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.categories_recyclerview_item,
                parent,
                false
            ), itemClickedListener
        )

    }

//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//
//        }
//
//    }

    inner class MyViewHolder(itemView: View, var itemClickedListener: ItemClickedListener) :
        BaseViewHolder<Category>(itemView) {
        val tv: TextView = itemView.category_tv;
        val iv: ImageView = itemView.categoryIv;
        override fun bind(t: Category, position: Int) {
            tv.text = t.name
            val imageLoader = ImageLoader.getInstance()
            imageLoader.displayImage(t.image, iv)
            itemView.setOnClickListener {
                Log.d("item","item clicked")
                itemClickedListener.itemClicked(t)
            }


        }
//        fun updateList(categoriesList: List<Category>){
//            this.categories = categoriesList
//            notifyDataSetChanged()
//        }


    }
}
