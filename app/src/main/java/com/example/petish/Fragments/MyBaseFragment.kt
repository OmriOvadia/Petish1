package com.example.petish.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView

import com.example.petish.Activities.ItemsActivity
import com.example.petish.Adapters.CategoriesListAdapter
import com.example.petish.Interface.ItemClickedListener
import com.example.petish.Model.Category
import com.example.petish.ViewModel.BaseFragmentViewModel
import com.jakewharton.rxbinding2.widget.RxTextView
import com.moveosoftware.infrastructure.mvvm.view.BaseFragment

abstract class MyBaseFragment : BaseFragment<BaseFragmentViewModel>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewStateObservable()

    }

    override fun getViewModelClass(): Class<BaseFragmentViewModel>  = BaseFragmentViewModel::class.java
    abstract fun initViewStateObservable()

    fun moveToItemActivity(type: Int, category: Category) {
        val intent1 = Intent(context, ItemsActivity::class.java)
        intent1.putExtra("categoryId", category._id)
        intent1.putExtra("type", type)
        Log.d(TAG, "moveToItemActivity: " + type + "," + category._id)
        startActivity(intent1)

    }

    fun initSearchObservable(searchEditText: EditText, type: Int) {
        mViewModel.setSearchObservable(RxTextView.textChangeEvents(searchEditText), type)
    }

    companion object {
        private val TAG = "MyBaseFragment"
    }
    fun getCategoriesFromDb(type: Int){
        mViewModel.getCategoriesFromDb(type)
    }



}
