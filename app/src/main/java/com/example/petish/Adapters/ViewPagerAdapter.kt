package com.example.petish.Adapters

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import java.util.ArrayList

@SuppressLint("WrongConstant")
open class ViewPagerAdapter(fm: FragmentManager, mFragmentList:ArrayList<Fragment>) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var mFragmentList: ArrayList<Fragment> = mFragmentList
    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }
}
