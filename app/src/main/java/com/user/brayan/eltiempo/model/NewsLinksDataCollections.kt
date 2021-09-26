package com.user.brayan.eltiempo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsLinksDataCollections(
    @SerializedName("rel")
    @Expose
    val rel: String?,

    @SerializedName("render")
    @Expose
    val render: String?,

    @SerializedName("href")
    @Expose
    val href: String?
)