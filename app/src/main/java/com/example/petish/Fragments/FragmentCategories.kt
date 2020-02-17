package com.example.petish.Fragments

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.petish.Model.MyTabLayout
import com.example.petish.R
import com.google.android.material.tabs.TabLayout
import com.moveosoftware.infrastructure.mvvm.view.SimpleFragment


class FragmentCategories(override val layout: Int) : SimpleFragment() {
    override fun initView(mRootView: ViewGroup) {
        tabLayout = mRootView.rootView.findViewById(R.id.tabLayout)
        fragmentContainer = mRootView.rootView.findViewById(R.id.fragment_container)
        addNewTab(tabLayout, DOG)
        addNewTab(tabLayout, CAT)
        initTabLayout()
    }

    private companion object{
        const val DOG = "Dog"
        const val CAT = "Cat"
    }
    private lateinit var  tabLayout: MyTabLayout
    private var fragmentContainer: FrameLayout? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showFragment(DogFragment())
    }

    private fun showFragment(f: Fragment) {
        if(!f.isAdded)
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, f).commit()
    }

    private fun addNewTab(tabLayout: MyTabLayout, title: String){
        val tab = tabLayout.newTab()
        tab.text = title;
        tabLayout.addTab(tab);
    }
    private fun initTabLayout(){
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val index = tab.position
                when (index) {

                    0 -> showFragment(DogFragment())
                    1 -> showFragment(CatFragment())
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }


}
