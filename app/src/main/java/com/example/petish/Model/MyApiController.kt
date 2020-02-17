package com.example.petish.Model

import com.example.petish.APIRequests.CategoriesApiRequest
import com.example.petish.APIRequests.CategorySearchApiRequest
import com.example.petish.BuildConfig
import com.example.petish.Interface.DataService
import com.example.petish.Repositories.CategoryRepository
import com.moveosoftware.infrastructure.mvvm.model.network.ApiController
import io.reactivex.Single

class MyApiController : ApiController<DataService>() {
    override fun getEndpoint(): String = ""
    //TODO ask TAMRA what is endpoint

    override fun getApiClass(): Class<DataService> = DataService::class.java


    fun getCategories(type: Int): Single<List<Category>> {
        return api.categories(CategoriesApiRequest(BuildConfig.TOKEN,type,1,10))
    }

    fun getCategoriesFromSearch(type: Int, charSequence: CharSequence): Single<List<Category>> {
        return api.categoriesSearch(charSequence, CategorySearchApiRequest(
            BuildConfig.TOKEN,type)
        )
    }


}