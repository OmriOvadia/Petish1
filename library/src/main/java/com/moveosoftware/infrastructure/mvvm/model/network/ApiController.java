package com.moveosoftware.infrastructure.mvvm.model.network;


import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by moveosoftware on 7/9/17
 */

public abstract class ApiController<T> {


    protected NetworkManager mNetworkManager;

    protected T api;


    public ApiController() {
        mNetworkManager = NetworkManager.getInstance();
        api = mNetworkManager.registerController(this);
    }


    protected <Response> ObservableTransformer<Response, Response> applyTransformer() {
        return applyTransformer(null);
    }

    private <Response> ObservableTransformer<Response, Response> applyTransformer(Scheduler responseScheduler) {
        return requestObservable ->
                requestObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(responseScheduler == null ? AndroidSchedulers.mainThread() : responseScheduler);
    }


    protected <Response> SingleTransformer<Response, Response> applyTransformerSingle() {
        return applyTransformerSingle(null);
    }

    private <Response> SingleTransformer<Response, Response> applyTransformerSingle(Scheduler responseScheduler) {
        return requestObservable ->
                requestObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(responseScheduler == null ? AndroidSchedulers.mainThread() : responseScheduler);
    }

    public abstract String getEndpoint();

    public abstract Class<T> getApiClass();


    protected boolean useHome() {
        return true;
    }

}
