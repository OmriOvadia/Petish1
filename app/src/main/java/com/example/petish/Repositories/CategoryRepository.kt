package com.example.petish.Repositories

import android.provider.SyncStateContract
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.petish.APIRequests.CategoriesApiRequest
import com.example.petish.APIRequests.CategorySearchApiRequest
import com.example.petish.BuildConfig
import com.example.petish.Interface.DataService
import com.example.petish.Model.Category
import com.example.petish.Model.PetishApplication
import com.example.petish.Network.RetrofitInstance
import com.example.petish.Room.CategoryDB
import com.example.petish.Utils.Constants
import com.moveosoftware.infrastructure.mvvm.model.network.ApiController
import com.moveosoftware.infrastructure.mvvm.model.network.NetworkManager
import io.reactivex.Single
import java.util.*

class CategoryRepository  {
    private val service: DataService = RetrofitInstance.create()
    private val db = PetishApplication.getDataBase()
    private val categoriesDAO = db?.categoryDao()


    fun getCategories(type: Int): Single<List<Category>> {
        return service.categories(CategoriesApiRequest(BuildConfig.TOKEN,type,1,10))
    }

    fun getCategoriesFromSearch(type: Int, charSequence: CharSequence):Single<List<Category>>{
        return service.categoriesSearch(charSequence, CategorySearchApiRequest(BuildConfig.TOKEN,type))
    }
    fun saveAllCategoriesToDB(categories: List<Category>, type: Int){
        for (i in categories){
            i.type = type
        }
        categoriesDAO?.insertAll(categories)
    }
    fun getCategoriesFromDBByType(type: Int): List<Category>?{
        return categoriesDAO?.getCategoriesBYType(type)

    }

    fun searchCategoriesDB(type: Int, charSequence: String):List<Category>?{
        return categoriesDAO?.searchCategories(charSequence,type)
    }

}