package com.moveosoftware.infrastructure.mvvm.model.network;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class ObserveOnMainCallAdapterFactory extends CallAdapter.Factory {
    final Scheduler scheduler;

    public ObserveOnMainCallAdapterFactory(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public CallAdapter<?, ?> get(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (getRawType(type) != Observable.class && getRawType(type) != Single.class && getRawType(type) != Completable.class && getRawType(type) != Flowable.class) {
            return null;
        }

        if (getRawType(type) == Observable.class) {
            return handleObservable(retrofit, type, annotations);
        }
        if (getRawType(type) == Single.class) {
            return handleSingle(retrofit, type, annotations);
        }
        if (getRawType(type) == Completable.class) {
            return handleCompletable(retrofit, type, annotations);
        }
        if (getRawType(type) == Flowable.class) {
            return handleFlowable(retrofit, type, annotations);
        }
        return null;
    }

    private CallAdapter<Object, Single<?>> handleSingle(Retrofit retrofit, Type type, Annotation[] annotations) {

        CallAdapter<Object, Single> delegate = (CallAdapter<Object, Single>) retrofit.nextCallAdapter(this, type, annotations);

        return new CallAdapter<Object, Single<?>>() {
            @Override
            public Type responseType() {
                return delegate.responseType();
            }

            @Override
            public Single<?> adapt(Call<Object> call) {
                Single<?> s = delegate.adapt(call);

                return s.observeOn(scheduler);
            }
        };
    }

    private CallAdapter<Object, Completable> handleCompletable(Retrofit retrofit, Type type, Annotation[] annotations) {
        CallAdapter<Object, Completable> delegate = (CallAdapter<Object, Completable>) retrofit.nextCallAdapter(this, type, annotations);

        return new CallAdapter<Object, Completable>() {
            @Override
            public Type responseType() {
                return delegate.responseType();
            }

            @Override
            public Completable adapt(Call<Object> call) {
                Completable s = delegate.adapt(call);

                return s.observeOn(scheduler);
            }
        };
    }

    private CallAdapter<Object, Flowable<?>> handleFlowable(Retrofit retrofit, Type type, Annotation[] annotations) {
        CallAdapter<Object, Flowable> delegate = (CallAdapter<Object, Flowable>) retrofit.nextCallAdapter(this, type, annotations);

        return new CallAdapter<Object, Flowable<?>>() {
            @Override
            public Type responseType() {
                return delegate.responseType();
            }

            @Override
            public Flowable<?> adapt(Call<Object> call) {
                Flowable<?> s = delegate.adapt(call);

                return s.observeOn(scheduler);
            }
        };
    }

    private CallAdapter<Object, Observable<?>> handleObservable(Retrofit retrofit, Type type, Annotation[] annotations) {
        CallAdapter<Object, Observable> delegate = (CallAdapter<Object, Observable>) retrofit.nextCallAdapter(this, type, annotations);

        return new CallAdapter<Object, Observable<?>>() {
            @Override
            public Type responseType() {
                return delegate.responseType();
            }

            @Override
            public Observable<?> adapt(Call<Object> call) {
                Observable<?> s = delegate.adapt(call);

                return s.observeOn(scheduler);
            }
        };
    }


}

