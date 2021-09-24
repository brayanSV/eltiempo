package com.user.brayan.eltiempo.repository

import androidx.lifecycle.LiveData
import com.user.brayan.eltiempo.AppExecutors
import com.user.brayan.eltiempo.api.ApiResponse
import com.user.brayan.eltiempo.db.NewsDao
import com.user.brayan.eltiempo.model.News
import javax.inject.Inject

class DetailsNewsRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val newsDao: NewsDao
) {
    /*fun loadDetails(nasaId: String): LiveData<Resource<News>> = object: NetworkBoundResource<News, News>(appExecutors) {
        override fun loadFromDataBase(): LiveData<News> {
            return newsDao.loadDetailData(nasaId)
        }

        override fun shouldFetch(data: News?): Boolean = data == null

        override fun saveCallResult(item: News) {}

        override fun favorite(favorite: Boolean) {
            newsDao.updateFavorite(favorite, nasaId)
        }

        override fun createCall(): LiveData<ApiResponse<News>>? {
            return null
        }

    }.asLiveData()*/
}