package com.user.brayan.eltiempo.db

import android.util.Log
import androidx.room.TypeConverter
import java.lang.NumberFormatException

object AppTypeConverter {
    @JvmStatic
    @TypeConverter
    fun stringToIntList(data: String?): List<Int>? =
        data?.let { it ->
            it.split(",").mapNotNull {
                try {
                    it.toInt()
                } catch (ex: NumberFormatException) {
                    Log.e("datos", "No puede convertit a numero")
                    null
                }
            }
        }

    @JvmStatic
    @TypeConverter
    fun intListToString(ints: List<Int>?): String? = ints?.joinToString ( "," )
}