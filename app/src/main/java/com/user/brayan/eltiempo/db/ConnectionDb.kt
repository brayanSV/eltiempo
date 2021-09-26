package com.user.brayan.eltiempo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.user.brayan.eltiempo.model.*

@Database(
    entities = [
        News::class
    ],
    version = 2
)

@TypeConverters(Converters::class)
abstract class ConnectionDb: RoomDatabase() {
    abstract fun noticeDao(): NewsDao
}