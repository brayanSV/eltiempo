package com.user.brayan.eltiempo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ItemsNewsCollections(
    @SerializedName("links")
    @Expose
    var links: List<NewsLinksDataCollections>,

    @SerializedName("href")
    @Expose
    var href: String?,

    @SerializedName("data")
    @Expose
    var data: NewsDataCollections
)