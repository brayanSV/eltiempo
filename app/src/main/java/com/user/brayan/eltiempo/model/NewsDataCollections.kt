package com.user.brayan.eltiempo.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.user.brayan.eltiempo.db.AppTypeConverter

data class NewsDataCollections(
    @SerializedName("date_created")
    @Expose
    val dateCreated: String?,

    @SerializedName("description")
    @Expose
    val description: String?,

    @SerializedName("nasa_id")
    @Expose
    val nasaId: String?,

    @SerializedName("keywords")
    @Expose
    val keywords: List<String>?,

    @SerializedName("center")
    @Expose
    val center: String?,

    @SerializedName("title")
    @Expose
    val title: String?,

    @SerializedName("media_type")
    @Expose
    val mediaType: String?
)
