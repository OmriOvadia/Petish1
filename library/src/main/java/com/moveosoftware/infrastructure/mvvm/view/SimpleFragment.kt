package com.moveosoftware.infrastructure.mvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Created by Ofer Dan-On on 10/01/2018
 */

abstract class  SimpleFragment : Fragment() {

    protected lateinit var mRootView: ViewGroup

    /**
     * Inflates the given layout and calls findViews(View layout)
     * eith the inflated view.
     *
     * @return The layout resource id to inflate on this fragment
     */
    abstract val layout: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater.inflate(layout, container, false) as ViewGroup

        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view as ViewGroup)
    }

    abstract fun initView(mRootView: ViewGroup)

}
