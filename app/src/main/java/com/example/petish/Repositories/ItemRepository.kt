package com.example.petish.Repositories

import androidx.lifecycle.LiveData
import com.example.petish.APIRequests.GetItemApiRequest
import com.example.petish.APIRequests.SetFavoriteApiRequest
import com.example.petish.BuildConfig
import com.example.petish.Interface.DataService
import com.example.petish.Model.Item
import com.example.petish.Model.PetishApplication
import com.example.petish.Network.RetrofitInstance
import io.reactivex.Single

class ItemRepository {
    private val db = PetishApplication.getDataBase()
    private val itemDao = db?.ItemDao()
    private val service: DataService = RetrofitInstance.create()

    fun getItemsByCat(categoryId: String, type: Int): Single<ArrayList<Item>>{
        return service.getItems(GetItemApiRequest(BuildConfig.TOKEN,categoryId,type,1,10))
    }

    fun setFavorite(isFavorite: Boolean, itemId: String): Single<Void>{
        return service.setFavorite(itemId,isFavorite, SetFavoriteApiRequest(BuildConfig.TOKEN))
    }
    fun getAllItems(type: Int): Single<ArrayList<Item>>{
        return service.getItems(GetItemApiRequest(BuildConfig.TOKEN,type,1,10))
    }
    fun getItemFromDbByCatId(catId: String): List<Item>?{
        return itemDao?.getItemFromDbByCatId(catId)
    }
    fun insertAllItemsToDb(items: ArrayList<Item>, categoryId: String){
        for (i in items){
            i.catId = categoryId
        }
        itemDao?.insertAll(items)
    }
    fun getAllFavoriteItemsFromDb():LiveData<List<Item>>?{
        return itemDao?.getAllFavoriteItems()
    }
    fun updatepItemOnDb(item: Item){
        itemDao?.updateItem(item);
    }
    fun getAllItemsFromDb():List<Item>?{
        return itemDao?.getAllItems()
    }

}
