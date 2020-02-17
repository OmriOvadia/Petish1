package com.example.petish.ViewModel


import android.util.Log

import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import com.example.petish.BuildConfig
import com.example.petish.Model.Item
import com.example.petish.Repositories.ItemRepository
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

class FavoriteFragmentViewModel : ViewModel() {
    var favoriteFragmentViewState: MutableLiveData<FavoriteFragmentViewState> = MutableLiveData();
    private var itemRepository: ItemRepository = ItemRepository()
    fun getAllFavoriteItems() {
        Log.d("FavoritesFragment", "getAllFavoriteItems()")
        favoriteFragmentViewState.postValue(FavoriteFragmentViewState.Loading)

        Single.zip(itemRepository.getAllItems(BuildConfig.TYPE_CAT),
            itemRepository.getAllItems(BuildConfig.TYPE_DOG),
            BiFunction<ArrayList<Item>, ArrayList<Item>, ArrayList<Item>> { dogItems, catItems ->
                return@BiFunction addLists(dogItems, catItems)

            }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<ArrayList<Item>> {
                override fun onSuccess(t: ArrayList<Item>) {
                    Log.d("item", t.size.toString())
                    var itemList = ArrayList<Item>()
                    for (i in t) {
                        if (i.isFavorite) {
                            itemList.add(i)
                        }
                    }
                    Log.d("itemFav", itemList.size.toString())
                    favoriteFragmentViewState.postValue(
                        FavoriteFragmentViewState.FavoriteItems(
                            itemList
                        )
                    );
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    favoriteFragmentViewState.postValue(FavoriteFragmentViewState.Error)
                }

            })
    }

    sealed class FavoriteFragmentViewState {
        data class FavoriteItems(var items: ArrayList<Item>) : FavoriteFragmentViewState()
        object Error : FavoriteFragmentViewState()
        object Loading : FavoriteFragmentViewState()
    }

    fun addLists(catItems: ArrayList<Item>, dogItems: ArrayList<Item>): ArrayList<Item> {
        var itemListTotal = ArrayList<Item>()
        itemListTotal.addAll(catItems)
        itemListTotal.addAll(dogItems)

        return itemListTotal;
    }


}