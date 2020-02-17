package com.example.petish.Fragments

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.petish.Adapters.FavoriteRecyclerViewAdapter
import com.example.petish.Model.Item
import com.example.petish.R
import com.example.petish.Utils.safeLet
import com.example.petish.ViewModel.FavoriteFragmentViewModel
import com.example.petish.ViewModel.ItemViewModel
import com.moveosoftware.infrastructure.mvvm.view.BaseFragment


import kotlin.collections.ArrayList

class FavoritesFragment : BaseFragment<ItemViewModel>() {


    private val TAG: String = "FavoritesFragment"
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavoriteRecyclerViewAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var noItemsTV: TextView

    override fun getViewModelClass(): Class<ItemViewModel> = ItemViewModel::class.java

    override fun getLayout(): Int = R.layout.favorites_fragment

    override fun initView(mRootView: ViewGroup) {
        recyclerView = mRootView.rootView.findViewById(R.id.favorites_fragment_recyclerview)
        adapter = FavoriteRecyclerViewAdapter()
        adapter.data = ArrayList()
        recyclerView.adapter = adapter
        noItemsTV = mRootView.rootView.findViewById(R.id.tv_favorite_fragment_no_items)
        progressBar = mRootView.rootView.findViewById(R.id.pb_favorite_fragment);
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDbObservable()
        mViewModel.getFavoriteItemsFromDb(this)

    }

    private fun initDbObservable() {

        mViewModel.getFavoriteLiveData().observe(this,
            Observer<ItemViewModel.Viewstate> { t ->
                when (t) {
                    is ItemViewModel.Viewstate.Error -> {
                        noItemsTV.visibility = View.VISIBLE
                        adapter.clear()
                        adapter.update(ArrayList())
                    }


                    is ItemViewModel.Viewstate.FavoriteItemsDB -> {
                        noItemsTV.visibility = View.GONE
                        adapter.clear()
                        adapter.data = t.items
                        adapter.notifyDataSetChanged()
                    }
                }
            })

    }

//    private fun initObservables() {
//        mViewModel.favoriteFragmentViewState.observe(this,
//            Observer<FavoriteFragmentViewModel.FavoriteFragmentViewState> { t ->
//                when (t) {
//                    is FavoriteFragmentViewModel.FavoriteFragmentViewState.FavoriteItems -> {
//                        progressBar.visibility = View.GONE
//                        noItemsTV.visibility = View.GONE
//                        adapter.setUpArray(t.items)
//                        Log.d(TAG,"getting all items")
//                    }
//                    is FavoriteFragmentViewModel.FavoriteFragmentViewState.Loading -> {
//                        adapter.setUpArray(ArrayList())
//                        progressBar.visibility = View.VISIBLE
//                    }
//
//                    is FavoriteFragmentViewModel.FavoriteFragmentViewState.Error -> {
//                        noItemsTV.visibility = View.VISIBLE
//                    }
//                }
//            })
//    }


}
