package com.user.brayan.eltiempo.api

import androidx.lifecycle.LiveData
import com.user.brayan.eltiempo.model.News
import retrofit2.http.GET
import retrofit2.http.Query

interface ApplicationApi {
    @GET("search/search?q=apollo%2011")
    fun loadNoticesDefault(): LiveData<ApiResponse<News>>

    @GET("search/")
    fun searchRepos(@Query("q") query: String): LiveData<ApiResponse<News>>
}