package com.example.petish.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.petish.R
import com.example.petish.Adapters.ViewPagerAdapter
import com.example.petish.Fragments.*
import com.example.petish.Model.MyTabLayout

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var mTabLayout: MyTabLayout
    private lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun setUpTabLayout(mTabLayout: MyTabLayout?, viewPager: ViewPager) {
        mTabLayout?.setupWithViewPager(viewPager)
        mTabLayout?.getTabAt(0)?.setIcon(R.drawable.group_4_1)
        mTabLayout?.getTabAt(1)?.setIcon(R.drawable.ic_shop)
        mTabLayout?.getTabAt(2)?.setIcon(R.drawable.heart)
        mTabLayout?.getTabAt(3)?.setIcon(R.drawable.ic_my_profile)
    }

    private fun setViewPager(viewPager: ViewPager) {

        var fragmentList: ArrayList<Fragment> = ArrayList()
        fragmentList.add(FragmentA(R.layout.fragment_a_layout))
        fragmentList.add(FragmentCategories(R.layout.fragment_categories_layout))
        fragmentList.add(FavoritesFragment())
        fragmentList.add(FragmentD(R.layout.fragment_d_layout))
        var viewPageAdapter = ViewPagerAdapter(supportFragmentManager, fragmentList);
        viewPager.adapter = viewPageAdapter;


    }
    private fun initViews(){
        viewPager = view_pager
        viewPager.offscreenPageLimit = 1
        mTabLayout = tab_layout_MainActivity
        setViewPager(viewPager)
        setUpTabLayout(mTabLayout, viewPager)

    }


}


