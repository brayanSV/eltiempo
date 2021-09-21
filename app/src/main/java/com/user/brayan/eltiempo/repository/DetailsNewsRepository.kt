package com.user.brayan.eltiempo.repository

import androidx.lifecycle.LiveData
import com.user.brayan.eltiempo.AppExecutors
import com.user.brayan.eltiempo.api.ApiResponse
import com.user.brayan.eltiempo.db.ConnectionDb
import com.user.brayan.eltiempo.db.NoticeDao
import com.user.brayan.eltiempo.model.News
import javax.inject.Inject

class DetailsNewsRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val connectionDb: ConnectionDb,
    private val noticeDao: NoticeDao,
) {
    fun loadDetails(nasaId: String): LiveData<Resource<News>> = object: NetworkBoundResource<News, News>(appExecutors) {
        override fun loadFromDataBase(): LiveData<News> {
            return noticeDao.loadDetailData(nasaId)
        }

        override fun shouldFetch(data: News?): Boolean {
            TODO("Not yet implemented")
        }

        override fun saveCallResult(item: News) {
            TODO("Not yet implemented")
        }

        override fun favorite(item: News) {
            TODO("Not yet implemented")
        }

        override fun createCall(): LiveData<ApiResponse<News>> {
            TODO("Not yet implemented")
        }
    }.asLiveData()
}