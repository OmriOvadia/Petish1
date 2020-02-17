package com.moveosoftware.infrastructure.mvvm.view;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.moveosoftware.infrastructure.mvvm.viewmodel.BaseViewModel;

/**
 * Created by Ofer Dan-On on 10/01/2018
 */

public abstract class BaseActivity<VM extends BaseViewModel> extends AppCompatActivity {

    protected VM mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(getViewModelClass());
        setContentView(getLayout());
        initView();
    }

    protected abstract Class<VM> getViewModelClass();

    protected abstract int getLayout();

    protected abstract void initView();


}
