package com.user.brayan.eltiempo.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(
    indices = [
        Index("href_nasaId")
    ],
    primaryKeys = [
        "href",
        "href_nasaId"
    ]
)

data class News(
    @Expose
    @field:SerializedName("href")
    val href: String,

    @Expose
    @field:SerializedName("data")
    @field:Embedded(prefix = "href_")
    val data: DataItemsNews
) {
    data class DataItemsNews(
        @field:SerializedName("date_created")
        @Expose
        val dateCreated: String,

        @field:SerializedName("description")
        @Expose
        val description: String,

        @field:SerializedName("nasa_id")
        @Expose
        val nasaId: String,

        @field:SerializedName("keywords")
        @Expose
        val keywords: List<String>,

        @field:SerializedName("center")
        @Expose
        val center: String,

        @field:SerializedName("title")
        @Expose
        val title: String,

        @field:SerializedName("media_type")
        @Expose
        val mediaType: String,

        @Expose
        val photo: String,

        @Expose
        var favorite: Boolean
    )
}