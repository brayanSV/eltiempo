package com.user.brayan.eltiempo.repository

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.google.gson.JsonObject
import com.user.brayan.eltiempo.AppExecutors
import com.user.brayan.eltiempo.api.ApiEmptyResponse
import com.user.brayan.eltiempo.api.ApiErrorResponse
import com.user.brayan.eltiempo.api.ApiResponse
import com.user.brayan.eltiempo.api.ApiSuccessResponse
import org.json.JSONObject

abstract class NetworkBoundResource <ResultType, RequestType>
@MainThread constructor(private val appExecutors: AppExecutors) {
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        val dbSource = loadFromDataBase()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)

            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    setValue(Resource.success(newData))
                }
            }
        }
    }

    @MainThread
    protected abstract fun loadFromDataBase(): LiveData<ResultType>

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        if (createCall() != null) {
            val apiResponse: LiveData<ApiResponse<JsonObject>> = createCall()!!
            result.addSource(dbSource) { newData ->
                setValue(Resource.loading(newData))
            }
            result.addSource(apiResponse) { response ->
                result.removeSource(apiResponse)
                result.removeSource(dbSource)
                when(response) {
                    is ApiSuccessResponse -> {
                        appExecutors.diskIo.execute {
                            saveCallResult(processResponse(response))
                            appExecutors.mainThread().execute {
                                result.addSource(loadFromDataBase()) { newData ->
                                    setValue(Resource.success(newData))
                                }
                            }
                        }
                    }

                    is ApiEmptyResponse -> {
                        appExecutors.mainThread.execute {
                            result.addSource(loadFromDataBase()) { newData ->
                                setValue(Resource.success(newData))
                            }
                        }
                    }

                    is ApiErrorResponse -> {
                        onFetchFailed()
                        result.addSource(dbSource) { newData ->
                            setValue(Resource.error(newData, response.errorMessage))
                        }
                    }
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    protected open fun onFetchFailed() {}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<JsonObject>) = response.body

    @WorkerThread
    protected abstract fun saveCallResult(item: JsonObject)

    @WorkerThread
    protected abstract fun favorite(favorite: Boolean)

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<JsonObject>>?
}