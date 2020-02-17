package com.example.petish.Network


import com.example.petish.BuildConfig
import com.example.petish.Interface.DataService

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
   // private var retrofit: Retrofit? = null
    fun create():DataService{
     val retrofit = Retrofit.Builder()
         .baseUrl(BuildConfig.BASE_URL)
         .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
         .addConverterFactory(GsonConverterFactory.create())

         .build()
     return retrofit.create(DataService::class.java)
 }
}
