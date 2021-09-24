package com.user.brayan.eltiempo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.user.brayan.eltiempo.db.AppTypeConverter

@Entity
@TypeConverters(AppTypeConverter::class)
data class KeyWords(
    @Expose
    val name: String
)
