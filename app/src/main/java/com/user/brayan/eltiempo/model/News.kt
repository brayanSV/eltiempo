package com.user.brayan.eltiempo.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.SerializedName

@Entity(
        indices = [
                Index("nasaId"),
                Index("nasaId_name")
        ],
        primaryKeys = [
                "nasaId",
                "nasaId_name"
        ]
)
data class News(
        @field:SerializedName("nasa_id")
        val nasaId: String,
        @field:SerializedName("href")
        val photo: String,
        @field:SerializedName("title")
        val title: String,
        @field:SerializedName("description")
        val description: String,
        @field:Embedded(prefix = "nasaId_")
        @field:SerializedName("keywords")
        val keyWords: KeyWords,
        val favorite: Boolean
)
