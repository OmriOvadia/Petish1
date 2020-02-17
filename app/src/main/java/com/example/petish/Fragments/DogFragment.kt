package com.example.petish.Fragments

import android.os.Bundle
import android.util.Log
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

class DogFragment : MyBaseFragment(), ItemClickedListener {
    override fun getLayout(): Int = R.layout.dog_fragment_categories_fragment


    override fun initView(mRootView: ViewGroup) {
        recyclerView = mRootView.rootView.findViewById(R.id.dog_categories_recycler_view)
        //recyclerView = mRootView.findViewById(R.id.dog_categories_recycler_view)
        adapter = CategoriesListAdapter(this)
        recyclerView.adapter = adapter
        // setRecyclerView(recyclerView,BuildConfig.TYPE_DOG,this)
        noResultTv = mRootView.rootView.findViewById(R.id.tv_dog_fragment_no_result)
        searchEditText = mRootView.rootView.findViewById(R.id.searchCat_et)
    }


    private val TAG = "DogFragment"
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var noResultTv: TextView
    private lateinit var adapter: CategoriesListAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearchObservable(searchEditText,BuildConfig.TYPE_DOG)
        getCategoriesFromDb(BuildConfig.TYPE_DOG)
    }

//    override fun onCreateView(
////        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
////        val view = inflater.inflate(R.layout.dog_fragment_categories_fragment, container, false)
//       // initViews(view)
//
//        initViewStateObservable()
//        initSearchObservable(searchEditText, BuildConfig.TYPE_DOG)
//        getCategoriesFromDb(BuildConfig.TYPE_DOG)
//
//
//    }





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
        moveToItemActivity(BuildConfig.TYPE_DOG, category)
    }


}
