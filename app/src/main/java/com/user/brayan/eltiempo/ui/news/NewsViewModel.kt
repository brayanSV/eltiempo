package com.user.brayan.eltiempo.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.user.brayan.eltiempo.model.News
import com.user.brayan.eltiempo.model.NewsCollections
import com.user.brayan.eltiempo.repository.NewsRepository
import com.user.brayan.eltiempo.repository.Resource
import com.user.brayan.eltiempo.utils.AbsentLiveData
import java.util.*
import javax.inject.Inject

class NewsViewModel @Inject constructor(val repository: NewsRepository): ViewModel() {
    //init news
    val repositories: LiveData<Resource<List<News>>> = repository.loadDefault()

    //search news
    private val query = MutableLiveData<String>()
    val queryLD: LiveData<String> = query

    val result: LiveData<Resource<List<News>>> = Transformations.switchMap(query) { search ->
        if (search.isNullOrBlank()) {
            repositories //aqui deberia hacer el cambio de tap
        } else {
            repository.search(search)
        }
    }

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

    fun favorite(favorite: Boolean, nasaId: String) {
        repository.favorite(favorite, nasaId)
    }

}