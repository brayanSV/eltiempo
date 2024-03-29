package com.user.brayan.eltiempo.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.*


class Converters {
    @TypeConverter
    fun listToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}