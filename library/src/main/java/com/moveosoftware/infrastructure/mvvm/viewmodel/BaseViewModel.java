package com.moveosoftware.infrastructure.mvvm.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.annotation.Nullable;

import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;


/**
 * Created by moveosoftware on 10/1/18
 */

public abstract class BaseViewModel extends ViewModel {

    protected <T> LiveData<T> getLiveData(Flowable<T> publisher) {
        MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
        MediatorLiveData<T> mainLiveData = new MediatorLiveData<>();
        mainLiveData.addSource(errorLiveData, new Observer<Throwable>() {
            @Override
            public void onChanged(@Nullable Throwable throwable) {

            }
        });
        publisher.subscribe(new FlowableSubscriber<T>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(T t) {
                mainLiveData.postValue(t);
            }

            @Override
            public void onError(Throwable t) {
                errorLiveData.postValue(t);
            }

            @Override
            public void onComplete() {

            }
        });
        return mainLiveData;
    }


}
