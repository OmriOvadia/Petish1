package com.example.petish.Activities

import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.petish.Adapters.ItemListRecyclerView
import com.example.petish.Model.Item
import com.example.petish.Interface.ItemRecyclerViewItemClick
import com.example.petish.R
import com.example.petish.ViewModel.ItemViewModel
import com.moveosoftware.infrastructure.mvvm.view.BaseActivity
import kotlinx.android.synthetic.main.activity_items.*


class ItemsActivity : BaseActivity<ItemViewModel>(), ItemRecyclerViewItemClick {
    override fun getViewModelClass(): Class<ItemViewModel> = ItemViewModel::class.java

    override fun getLayout(): Int {
       return R.layout.activity_items
    }
    private companion object{
        const val CATEGORY_ID: String = "categoryId"
        const val TYPE = "type"
    }
    private lateinit var noResultRL: RelativeLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemListRecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      // setContentView(R.layout.activity_items)

        initObservable()
    }

    override fun initView() {
        mViewModel.type = intent.getIntExtra(TYPE, 0)
        mViewModel.categoryId = intent.getStringExtra(CATEGORY_ID)
        recyclerView = items_activity_recyclerView
        noResultRL = rl_no_result_found_text_view
        adapter = ItemListRecyclerView( this)
        adapter.data = mViewModel.getAllItemsFromDbByCat()
        var gridLayoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = gridLayoutManager
    }
    private fun initObservable(){
        mViewModel.fetchItemsFromRepo().observe(this,
            Observer<ItemViewModel.Viewstate> { t ->
                when(t){
                    is ItemViewModel.Viewstate.Loading ->  noResultRL.visibility = View.GONE
                    is  ItemViewModel.Viewstate.Error -> {
                        noResultRL.visibility = View.VISIBLE

                    }
                    is ItemViewModel.Viewstate.ItemList -> {
                        noResultRL.visibility = View.GONE
                        adapter.update(t.items)
                    }
                }
            })
    }

    override fun itemClicked(item: Item, position: Int) {
        mViewModel.setFavorite(item, adapter)
    }
}
