package com.user.brayan.eltiempo.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.google.gson.JsonObject
import com.user.brayan.eltiempo.AppExecutors
import com.user.brayan.eltiempo.api.ApiResponse
import com.user.brayan.eltiempo.api.ApplicationApi
import com.user.brayan.eltiempo.db.ConnectionDb
import com.user.brayan.eltiempo.utils.ConvertJsonToData
import com.user.brayan.eltiempo.db.NewsDao
import com.user.brayan.eltiempo.model.News
import com.user.brayan.eltiempo.utils.AbsentLiveData
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val newsDao: NewsDao,
    private val applicationApi: ApplicationApi
) {
    fun loadDefault(): LiveData<Resource<List<News>>> = object: NetworkBoundResource<List<News>, List<News>>(appExecutors) {
        override fun loadFromDataBase(): LiveData<List<News>> {
            return newsDao.loadData()
        }

        override fun shouldFetch(data: List<News>?): Boolean {
            return data == null || data.isEmpty()
        }

        override fun saveCallResult(item: JsonObject) {
            val listItems = ConvertJsonToData().convert(item.toString())
            newsDao.insert(listItems)
        }

        override fun createCall(): LiveData<ApiResponse<JsonObject>>? {
            return applicationApi.loadNoticesDefault()
        }

    }.asLiveData()

    fun search(query: String): LiveData<Resource<List<News>>> = object: NetworkBoundResource<List<News>, List<News>>(appExecutors) {
        override fun loadFromDataBase(): LiveData<List<News>> {
            return Transformations.switchMap(newsDao.searchData(query)) { searchData ->
                if (searchData == null) {
                    AbsentLiveData.create()
                } else {
                    newsDao.searchData(query)
                }
            }
        }

        override fun shouldFetch(data: List<News>?): Boolean {
            return data == null || data.isEmpty()
        }

        override fun saveCallResult(item: JsonObject) {
            val listItems = ConvertJsonToData().convert(item.toString())
            newsDao.insert(listItems)
        }

        override fun createCall(): LiveData<ApiResponse<JsonObject>>? {
            return applicationApi.searchRepos(query)
        }
    }.asLiveData()

    fun favorite(isFavorite: Boolean, nasaId: String) {
        newsDao.updateFavorite(isFavorite, nasaId)
    }
}