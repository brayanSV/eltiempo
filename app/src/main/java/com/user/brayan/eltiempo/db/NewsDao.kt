package com.user.brayan.eltiempo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.user.brayan.eltiempo.model.News
import com.user.brayan.eltiempo.model.NewsCollections

@Dao
abstract class NewsDao {
    @JvmSuppressWildcards
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(item: List<News>)

    @Query("select * from News")
    abstract fun loadData(): LiveData<List<News>>

    @Query("update news set href_favorite = :favorite where href_nasaId = :nasa_id ")
    abstract fun updateFavorite(favorite: Boolean, nasa_id: String)

    @Query("select * from news where href_nasaId = :nasa_id")
    abstract fun loadDetailData(nasa_id: String): LiveData<News>

    @Query("select * from news where `href_keywords` like '%' || :query || '%'")
    abstract fun searchData(query: String): LiveData<List<News>>
}