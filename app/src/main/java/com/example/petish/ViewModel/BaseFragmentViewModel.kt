package com.example.petish.ViewModel

import android.annotation.SuppressLint
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.petish.Model.Category
import com.example.petish.Model.PetishApplication
import com.example.petish.Repositories.CategoryRepository
import com.example.petish.Room.CategoryDB
import com.example.petish.Utils.safeLet
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import com.moveosoftware.infrastructure.mvvm.viewmodel.BaseViewModel
import io.reactivex.Observable
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class BaseFragmentViewModel : BaseViewModel(){
    private var liveData: MutableLiveData<BaseFragmentViewState> = MutableLiveData()
    private var categoryRepo = CategoryRepository()

    fun getLiveData() : MutableLiveData<BaseFragmentViewState> {
        return liveData
    }

     private fun getCategories(charSequence: CharSequence, type: Int){
         Log.d("MyBaseFragment",charSequence.toString())
        if(charSequence.isEmpty()){
            Log.d("MyBaseFragment","all categories")
            getAllCategories(type)

        }else{
            getCategoriesBySearch(type,charSequence)
            Log.d("MyBaseFragment","searching")

        }
    }


    private fun getCategoriesBySearch(type: Int, charSequence: CharSequence){
        liveData.postValue(BaseFragmentViewState.Loading)
        var s  = "$charSequence%"

        var cats = categoryRepo.searchCategoriesDB(type,s)
        if(cats!!.isEmpty()){
            liveData.postValue(BaseFragmentViewState.Error)
        }else{
            liveData.postValue(BaseFragmentViewState.SearchedCategories(cats))

        }



    }
    private  fun getAllCategories(type: Int){
         liveData.postValue(BaseFragmentViewState.Loading)
         categoryRepo.getCategories(type).subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(object: SingleObserver<List<Category>>{
                 override fun onSuccess(t: List<Category>) {
                     processResult(t,type)

                 }

                 override fun onSubscribe(d: Disposable) {
                     Log.d("viewModel","onSubscribe")

                 }

                 override fun onError(e: Throwable) {
                     Log.d("viewModel","onError")
                     liveData.postValue(BaseFragmentViewState.Error)
                 }

             })
    }



    @SuppressLint("CheckResult")
    fun setSearchObservable(observable: Observable<TextViewTextChangeEvent>,type: Int){

        observable.subscribe {

            getCategories(it.text().toString(),type)
        }

    }

    private fun processResult(categories: List<Category>, type: Int){
        val dbCategories = categoryRepo.getCategoriesFromDBByType(type)
        if(!isListsEquals(dbCategories!!,categories)){
            liveData.postValue(BaseFragmentViewState.CategoryList(categories))
            categoryRepo.saveAllCategoriesToDB(categories,type)
        }else{
            liveData.postValue(BaseFragmentViewState.CategoryList(categories))
        }
    }


    private fun isListsEquals(listA: List<Category>, listB: List<Category>):Boolean{


        if(listA.size == listB.size){
            for (i in 0..listA.size){
                if(listA[0] != listB[0]){
                   return false
                }
                return true
            }
        }
        return false

    }

    fun getCategoriesFromDb(type: Int) {
        liveData.postValue(BaseFragmentViewState.CategoriesFromDb(categoryRepo.getCategoriesFromDBByType(type)))
    }

    sealed class BaseFragmentViewState{
        object Loading : BaseFragmentViewState()
        object Error: BaseFragmentViewState()
        data class CategoryList(var categories: List<Category>) : BaseFragmentViewState()
        data class SearchedCategories(var categories: List<Category>) : BaseFragmentViewState()
        data class CategoriesFromDb(var categories: List<Category>?): BaseFragmentViewState()
    }

    /*        categoryRepo.getCategoriesFromSearch(type,charSequence).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: SingleObserver<List<Category>>{
                override fun onSuccess(t: List<Category>) {

                    liveData.postValue(BaseFragmentViewState.CategoryList(t))

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    liveData.postValue(BaseFragmentViewState.Error)
                }
            })*/
}