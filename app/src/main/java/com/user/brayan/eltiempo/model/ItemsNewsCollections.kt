package com.user.brayan.eltiempo.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ItemsNewsCollections(
    @PrimaryKey(autoGenerate = true)
    @Expose
    var itemId: Int,

    @SerializedName("links")
    @Expose
    var links: List<NewsLinksDataCollections>?,

    @SerializedName("href")
    @Expose
    var href: String?,

    @SerializedName("data")
    @Expose
    var data: List<NewsDataCollections>
)