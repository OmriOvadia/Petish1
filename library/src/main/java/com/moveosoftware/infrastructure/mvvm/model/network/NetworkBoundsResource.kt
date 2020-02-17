package com.moveosoftware.infrastructure.mvvm.model.network

import android.content.Context
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.exceptions.Exceptions

import java.io.IOException
import java.util.*


/**
 * Created by eliran on 26/02/2018.
 *
 */
abstract class NetworkBoundResource<ResultType, RequestType>(context: Context) {

    private val result: Observable<Resource<ResultType>>


    init {
        // Lazy disk observable.
        val diskObservable = Observable.defer {
            loadFromDb()
                    .map<Resource<ResultType>> { Resource.Success(it, false) }
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
        }


        // Lazy network observable.
        val networkObservable = Observable.defer {
            createCall()
                    .doOnNext { request ->
                        saveCallResult(request)
                    }
                    //Clear old data if fetch new data from server
                    .doOnTerminate { clearOldData() }
                    .onErrorReturn { throwable: Throwable ->
                        when (throwable) {
                            is retrofit2.HttpException -> {
                                throw Exceptions.propagate(NetworkExceptions.getNoServerConnectivityError(context))
                            }
                            is IOException -> {
                                throw Exceptions.propagate(NetworkExceptions.getNoNetworkConnectivityError(context))
                            }
                            else -> {
                                throw Exceptions.propagate(NetworkExceptions.getUnexpectedError(context))
                            }
                        }
                    }
                    .flatMap { loadFromDb().map<Resource<ResultType>> { Resource.Success(it, true) } }
                    .mergeWith(loadFromDb().map<Resource<ResultType>> { Resource.Success(it, false) })
        }

        result = when {
            context.isNetworkStatusAvailable() -> networkObservable
                    .onErrorReturn { Resource.Failure(it) }
                    // Read results in Android Main Thread (UI)
                    .observeOn(AndroidSchedulers.mainThread())
                    .startWith(Resource.Loading())
            else -> diskObservable
                    .onErrorReturn { Resource.Failure(it) }
                    // Read results in Android Main Thread (UI)
                    .observeOn(AndroidSchedulers.mainThread())
                    .startWith(Resource.Loading())
        }
    }



    fun asObservable(): Observable<Resource<ResultType>> {
        return result
    }


    protected abstract fun createCall(): Observable<RequestType>

    protected abstract fun loadFromDb(): Observable<ResultType>

    protected abstract fun saveCallResult(request: RequestType)

    protected abstract fun clearOldData(date: Date = Date())

//    protected abstract fun addNode(throwable: Throwable): NodeRealmObject?

}