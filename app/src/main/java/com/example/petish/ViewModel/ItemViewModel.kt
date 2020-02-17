package com.example.petish.ViewModel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.petish.Adapters.ItemListRecyclerView
import com.example.petish.Model.Item
import com.example.petish.Repositories.ItemRepository
import com.moveosoftware.infrastructure.mvvm.viewmodel.BaseViewModel
import java.util.ArrayList
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers

class ItemViewModel : BaseViewModel() {

    val liveData: MutableLiveData<Viewstate> = MutableLiveData()
    private val favoriteFragmentliveData: MutableLiveData<Viewstate> = MutableLiveData()
    private var itemRepository: ItemRepository = ItemRepository()
    var categoryId: String? = null
    var type: Int = 0

   fun getFavoriteLiveData(): MutableLiveData<Viewstate>{
       return favoriteFragmentliveData
   }

//    fun fetchItemsFromRepo(): MutableLiveData<Viewstate> {
    fun fetchItemsFromRepo() {
       liveData.postValue( Viewstate.Loading)
    val subscribe = itemRepository.getItemsByCat(categoryId!!, type)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .filter { items ->
            val list = itemRepository.getItemFromDbByCatId(categoryId!!)

            !areListsEquals(list, items)
        }
        .subscribe { items ->
            liveData.postValue(Viewstate.ItemList(items))
            itemRepository.insertAllItemsToDb(items, categoryId!!)
        }
//            .subscribe(object : SingleObserver<ArrayList<Item>> {
//                override fun onSubscribe(d: Disposable) {
//
//                }
//
//                override fun onSuccess(itemList: ArrayList<Item>) {
//                    processResults(itemList, categoryId!!)
//                  //  liveData.value = Viewstate.ItemList(itemList)
//
//                }
//
//                override fun onError(e: Throwable) {
//                   liveData.value = Viewstate.Error
//                }
//            })

    }

    fun setFavorite(
        item: Item,
        adapter: ItemListRecyclerView
    ):MutableLiveData<Viewstate> {
       // liveData.postValue(Viewstate.setFavoriteLoading)

        itemRepository.setFavorite(!item.isFavorite, item._id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Void> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onSuccess(aVoid: Void) {
                    Log.d(TAG, "onSuccess ")

                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onError ")
                    item.isFavorite = !item.isFavorite
                    //TODO to move this to the UI - adapter
                    adapter.update(item)
                    item.catId = categoryId!!
                    itemRepository.updatepItemOnDb(item)

                }
            })
        return liveData
    }





        sealed class Viewstate{
            object setFavoriteLoading: Viewstate()
            object Loading : Viewstate()
            object Error: Viewstate()
            data class ItemList(var items: ArrayList<Item>): Viewstate()
            data class FavoriteItemsDB(var items: List<Item>) : Viewstate()

        }
    fun processResults(items: ArrayList<Item>, categoryId: String){
        val list = itemRepository.getItemFromDbByCatId(categoryId)
        if(!areListsEquals(list,items)){
            //liveData.postValue(Viewstate.ItemList(items))
            liveData.postValue(Viewstate.ItemList(items))
            itemRepository.insertAllItemsToDb(items,categoryId)
        }


    }

    private fun areListsEquals(list: List<Item>?, items: ArrayList<Item>): Boolean {
        if(list?.size == items.size){
            for (i in list.indices){
                if(list[i] != items[i])
                    return false
            }
            return true
        }
        return false
    }


    companion object {
        private val TAG = "ItemActivityViewModel"
    }



    fun getFavoriteItemsFromDb(lifeOwner: LifecycleOwner) = itemRepository.getAllFavoriteItemsFromDb()!!.observe(lifeOwner,
        Observer<List<Item>> { t ->
            when (t!!.size){
                0 -> {
                    favoriteFragmentliveData.postValue(Viewstate.Error)

                }
                else -> {
                    favoriteFragmentliveData.postValue(Viewstate.FavoriteItemsDB(t))

                }
            }
        })

    fun getAllItemsFromDbByCat() = itemRepository.getItemFromDbByCatId(categoryId!!)



}
