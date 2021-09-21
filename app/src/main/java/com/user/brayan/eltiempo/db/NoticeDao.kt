package com.user.brayan.eltiempo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.user.brayan.eltiempo.model.News

@Dao
abstract class NoticeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(item: List<News>)

    @Query("update news set favorite = :favorite where nasaId = :nasa_id")
    abstract fun updateFavorite(favorite: Boolean, nasa_id: String)

    @Query("select * from news limit 100")
    abstract fun loadData(): LiveData<List<News>>

    @Query("select * from news where nasaId = :nasa_id")
    abstract fun loadDetailData(nasa_id: String): LiveData<News>

    @Query("select * from news where nasa_id_name like '%'|:query|'%'")
    abstract fun searchData(query: String): LiveData<List<News>>?
}