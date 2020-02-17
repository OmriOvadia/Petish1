package com.example.petish.APIRequests

import com.moveosoftware.infrastructure.mvvm.model.network.Request

data class CategorySearchApiRequest(var token: String, var type: Int): Request()