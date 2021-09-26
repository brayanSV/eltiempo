package com.user.brayan.eltiempo.repository

import androidx.lifecycle.LiveData
import com.google.gson.JsonObject
import com.user.brayan.eltiempo.AppExecutors
import com.user.brayan.eltiempo.api.ApiResponse
import com.user.brayan.eltiempo.api.ApplicationApi
import com.user.brayan.eltiempo.utils.ConvertJsonToData
import com.user.brayan.eltiempo.db.NewsDao
import com.user.brayan.eltiempo.model.News
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val newsDao: NewsDao,
    private val applicationApi: ApplicationApi
) {
    fun loadDefault(nasaId: String?): LiveData<Resource<List<News>>> = object: NetworkBoundResource<List<News>, List<News>>(appExecutors) {
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

        override fun favorite(favorite: Boolean) {
            newsDao.updateFavorite(favorite, nasaId!!)
        }

        override fun createCall(): LiveData<ApiResponse<JsonObject>>? {
            return applicationApi.loadNoticesDefault()
        }

    }.asLiveData()
}