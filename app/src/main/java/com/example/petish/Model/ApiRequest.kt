package com.example.petish.Model

class ApiRequest {
     var token: String? = null
    private var type: Int = 0
    private var page: Int = 0
    private var limit: Int = 0
    private var categoryId: String? = null;


    constructor(token: String, type: Int, page: Int, limit: Int) {
        this.token = token
        this.type = type
        this.page = page
        this.limit = limit
    }

    constructor(token: String, type: Int) {
        this.token = token
        this.type = type
    }

    constructor(token: String, categoryId: String, type: Int, page: Int, limit: Int) {
        this.token = token
        this.type = type
        this.page = page
        this.limit = limit
        this.categoryId = categoryId
    }

    constructor(token: String) {
        this.token = token
    }

}


