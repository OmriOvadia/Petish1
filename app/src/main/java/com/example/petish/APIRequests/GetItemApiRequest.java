package com.example.petish.APIRequests;

import com.moveosoftware.infrastructure.mvvm.model.network.Request;

public class GetItemApiRequest extends Request {
    private String token, categoryId;
    private int type,page,limit;

    public GetItemApiRequest(String token, String categoryId, int type, int page, int limit) {
        this.token = token;
        this.categoryId = categoryId;
        this.type = type;
        this.page = page;
        this.limit = limit;
    }

    public GetItemApiRequest(String token, int type, int page, int limit) {
        this.token = token;
        this.type = type;
        this.page = page;
        this.limit = limit;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCatID() {
        return categoryId;
    }

    public void setCatID(String categoryId) {
        this.categoryId = categoryId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
