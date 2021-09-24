package com.user.brayan.eltiempo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.user.brayan.eltiempo.model.*

@Database(
    entities = [
        News::class
    ],
    version = 3
)
abstract class ConnectionDb: RoomDatabase() {
    abstract fun noticeDao(): NewsDao
}