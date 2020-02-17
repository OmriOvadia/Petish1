package com.example.petish.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.petish.Model.IsValidWrapper;
import com.example.petish.R;
import com.moveosoftware.infrastructure.mvvm.view.BaseActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function3;

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    @Override
    protected Class getViewModelClass() {
        return null;
    }

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void initView() {

        Observable
                .combineLatest(Observable.just(""), Observable.just(""), Observable.just(""), new Function3<String, String, String, IsValidWrapper>() {
                    @Override
                    public IsValidWrapper apply(String s, String s2, String s3) throws Exception {
                        return new IsValidWrapper(s.equals(""), s2.equals(""), s3.equals(""));
                    }
                })
                .subscribe(new Observer<IsValidWrapper>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(IsValidWrapper isValidWrapper) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
