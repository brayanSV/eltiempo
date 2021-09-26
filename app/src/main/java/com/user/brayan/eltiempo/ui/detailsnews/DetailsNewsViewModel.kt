package com.user.brayan.eltiempo.ui.detailsnews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.user.brayan.eltiempo.model.News
import com.user.brayan.eltiempo.repository.DetailsNewsRepository
import com.user.brayan.eltiempo.repository.Resource
import javax.inject.Inject

class DetailsNewsViewModel @Inject constructor(val repository: DetailsNewsRepository): ViewModel() {
    val nasaId = MutableLiveData<String>()

    val detailsNews: LiveData<Resource<News>> = Transformations.switchMap(nasaId) { news ->
        repository.loadDetails(news)
    }

    fun favorite(favorite: Boolean, nasaId: String) {
        repository.favorite(favorite, nasaId)
    }
}