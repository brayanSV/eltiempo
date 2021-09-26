package com.user.brayan.eltiempo.di

import android.app.Application
import androidx.room.Room
import com.user.brayan.eltiempo.api.ApplicationApi
import com.user.brayan.eltiempo.db.ConnectionDb
import com.user.brayan.eltiempo.db.NewsDao
import com.user.brayan.eltiempo.utils.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideApplicationApi(): ApplicationApi {
        return Retrofit.Builder()
            .baseUrl("https://images-api.nasa.gov/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(ApplicationApi::class.java)
    }

    @Provides
    fun provideDb(app: Application): ConnectionDb {
        return Room.databaseBuilder(app, ConnectionDb::class.java, "connectionDb.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsDao(db: ConnectionDb): NewsDao {
        return db.noticeDao()
    }
}