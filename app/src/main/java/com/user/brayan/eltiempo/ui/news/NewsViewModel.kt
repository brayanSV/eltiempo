package com.user.brayan.eltiempo.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.user.brayan.eltiempo.model.News
import com.user.brayan.eltiempo.repository.NewsRepository
import com.user.brayan.eltiempo.repository.Resource
import java.util.*
import javax.inject.Inject

class NewsViewModel @Inject constructor(repository: NewsRepository): ViewModel() {
    //init news
    private val _accountType: MutableLiveData<News> = MutableLiveData()
    val accountType: LiveData<News> get() = _accountType

    val repositories: LiveData<Resource<List<News>>> = repository.loadDefault(null)

    //search news
    private val query = MutableLiveData<String>()
    val queryLD: LiveData<String> = query

    fun refresh() {
        query.value?.let {
            query.value = it
        }
    }

    fun setQuery(originalInput: String) {
        val input = originalInput.toLowerCase(Locale.getDefault()).trim()
        if (input == query.value) {
            return
        }

        query.value = input
    }
}