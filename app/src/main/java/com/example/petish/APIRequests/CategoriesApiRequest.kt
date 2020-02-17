package com.example.petish.APIRequests

import com.example.petish.Model.ApiRequest
import com.moveosoftware.infrastructure.mvvm.model.network.Request

data class CategoriesApiRequest(var token:String, var type: Int, var page: Int, var limit: Int): Request()