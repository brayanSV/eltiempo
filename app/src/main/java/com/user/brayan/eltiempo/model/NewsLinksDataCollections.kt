package com.user.brayan.eltiempo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.user.brayan.eltiempo.db.AppTypeConverter

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