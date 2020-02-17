package com.moveosoftware.infrastructure.mvvm.view;


import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moveosoftware.infrastructure.mvvm.viewmodel.BaseViewModel;

/**
 * Created by Ofer Dan-On on 10/01/2018
 */
public abstract class BaseFragment<VM extends BaseViewModel> extends Fragment {

    protected ViewGroup mRootView;
    protected VM mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = (ViewGroup) inflater.inflate(getLayout(), container, false);
        mViewModel = ViewModelProviders.of(this).get(getViewModelClass());
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView((ViewGroup) view);
    }

    public abstract Class<VM> getViewModelClass();


    /**
     * Inflates the given layout and calls findViews(View layout)
     * eith the inflated view.
     *
     * @return The layout resource id to inflate on this fragment
     */
    public abstract int getLayout();


    public abstract void initView(ViewGroup mRootView);

}
