package com.example.petish.Interface

import com.example.petish.APIRequests.*
import com.example.petish.Model.Category
import com.example.petish.Model.Item
import com.moveosoftware.infrastructure.mvvm.model.network.ApiController
import io.reactivex.Single

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface DataService  {


    @POST("getItems")
    fun getItems(@Body request: GetItemApiRequest): Single<ArrayList<Item>>

    @POST("categories")
    fun categories(@Body apiRequest: CategoriesApiRequest): Single<List<Category>>

    @POST("categoriesSearch/{term} ")
    fun categoriesSearch(@Path("term") term: CharSequence, @Body apiRequest: CategorySearchApiRequest): Single<List<Category>>

    @PUT("setFavorite/{itemId}/{isFavorite}")
    fun setFavorite(@Path("itemId") itemId: String, @Path("isFavorite") isFavorite: Boolean, @Body apiRequest: SetFavoriteApiRequest): Single<Void>




}
