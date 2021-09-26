package com.user.brayan.eltiempo.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.user.brayan.eltiempo.AppExecutors
import com.user.brayan.eltiempo.api.ApiResponse
import com.user.brayan.eltiempo.db.NewsDao
import com.user.brayan.eltiempo.model.News
import javax.inject.Inject

class DetailsNewsRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val newsDao: NewsDao
) {
    fun loadDetails(nasaId: String): LiveData<Resource<News>> = object: NetworkBoundResource<News, News>(appExecutors) {
        override fun loadFromDataBase(): LiveData<News> {
            return newsDao.loadDetailData(nasaId)
        }

        override fun shouldFetch(data: News?): Boolean = data == null

        override fun saveCallResult(item: JsonObject) {}

        override fun createCall(): LiveData<ApiResponse<JsonObject>>? = null
    }.asLiveData()

    fun favorite(isFavorite: Boolean, nasaId: String) {
        Log.e("datos", "isFavorite: $isFavorite, nasaId: $nasaId")

        newsDao.updateFavorite(isFavorite, nasaId)
    }
}