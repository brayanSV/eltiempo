package com.user.brayan.eltiempo.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsCollections(
    @field:SerializedName("items")
    @Expose
    var collection: List<ItemsNewsCollections>
)