package com.moveosoftware.infrastructure.mvvm.view;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.moveosoftware.infrastructure.mvvm.viewmodel.BaseViewModel;


/**
 * Created by Ofer Dan-On on 10/01/2018
 */
public abstract class BaseDialog<VM extends BaseViewModel> extends DialogFragment {

    protected ViewGroup mRootView;
    protected VM mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = (ViewGroup) inflater.inflate(getLayout(), container, false);
        mViewModel = ViewModelProviders.of(this).get(getViewModelClass());
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initView(mRootView);
        return mRootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
