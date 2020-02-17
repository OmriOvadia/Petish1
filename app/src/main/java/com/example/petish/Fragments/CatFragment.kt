package com.example.petish.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.petish.Adapters.CategoriesListAdapter
import com.example.petish.BuildConfig

import com.example.petish.Model.Category
import com.example.petish.Interface.ItemClickedListener
import com.example.petish.R
import com.example.petish.ViewModel.BaseFragmentViewModel

class CatFragment : MyBaseFragment(), ItemClickedListener {

    private val TAG = "FragmentCat"
    private lateinit var recyclerView: RecyclerView
    private lateinit var editText: EditText
    private lateinit var noResultTv: TextView
    private lateinit var adapter: CategoriesListAdapter

    override fun getLayout(): Int  = R.layout.cat_fragment_categories_fragment

    override fun initView(mRootView: ViewGroup) {
        editText = mRootView.rootView.findViewById(R.id.searchCat_et_cat)
        recyclerView = mRootView.rootView.findViewById(R.id.cat_categories_recycler_view)
        adapter = CategoriesListAdapter(this)
        recyclerView.adapter = adapter
        noResultTv = mRootView.rootView.findViewById(R.id.tv_cat_fragment_no_result);
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearchObservable(editText,BuildConfig.TYPE_CAT)
        getCategoriesFromDb(BuildConfig.TYPE_CAT)
    }

    override fun initViewStateObservable() {
        mViewModel.getLiveData()
            .observe(this,
                Observer<BaseFragmentViewModel.BaseFragmentViewState> {
                    when (it) {
                        is BaseFragmentViewModel.BaseFragmentViewState.Loading -> {

                        }
                        is BaseFragmentViewModel.BaseFragmentViewState.Error -> {
                            noResultTv.visibility = View.VISIBLE
                            adapter.clear()
                            adapter.update(listOf())
                        }
                        is BaseFragmentViewModel.BaseFragmentViewState.CategoryList -> {
                            noResultTv.visibility = View.GONE
                            adapter.clear()
                            adapter.update(it.categories)
                        }
                        is BaseFragmentViewModel.BaseFragmentViewState.SearchedCategories -> {
                            noResultTv.visibility = View.GONE
                            adapter.clear()
                            adapter.update(it.categories)
                        }
                        is BaseFragmentViewModel.BaseFragmentViewState.CategoriesFromDb -> {
                            adapter.clear()
                            adapter.update(it.categories!!)
                            Log.d(TAG, "CategoriesFromDB")
                        }
                    }
                })
    }


    override fun itemClicked(category: Category) {
        moveToItemActivity(BuildConfig.TYPE_CAT, category)
    }


}
