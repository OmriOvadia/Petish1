package com.example.petish.Model

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.petish.Room.DataBase.AppDataBase
import com.moveosoftware.infrastructure.BaseApplication
import com.moveosoftware.infrastructure.BuildConfig
import com.moveosoftware.infrastructure.mvvm.model.network.ApiConfig
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration

class PetishApplication : BaseApplication() {
    override fun getApiConfig(): ApiConfig {
        return ApiConfig.Builder(com.example.petish.BuildConfig.BASE_URL)
            .build()
    }

    companion object{
        var db: AppDataBase? = null;
        fun getDataBase():AppDataBase?{
            return db
        }
    }

    override fun onCreate() {
        super.onCreate()
        initImageLoader()
        db = Room.databaseBuilder(applicationContext,
            AppDataBase::class.java,
            "DB").allowMainThreadQueries()
            .fallbackToDestructiveMigration().build()

    }

    fun initImageLoader(){
        val imageLoader = ImageLoader.getInstance()
        val displayImageOptions = DisplayImageOptions.Builder().cacheInMemory(true)
            .cacheOnDisk(true)
            .build()
        val configuration = ImageLoaderConfiguration.Builder(this)
            .defaultDisplayImageOptions(displayImageOptions)
            .build()
        imageLoader.init(configuration)
    }
}