package com.test.testtask65apps.di

import android.app.Application
import androidx.room.Room
import com.test.testtask65apps.TLSSocketFactory
import com.test.testtask65apps.network.Api
import com.test.testtask65apps.room.AppDatabase
import com.test.testtask65apps.room.WorkerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Api {
        val socketFactory = TLSSocketFactory()
        return Retrofit.Builder()
            .baseUrl("https://gitlab.65apps.com/65gb/static/raw/master/")
            .client(
                OkHttpClient.Builder().sslSocketFactory(socketFactory, socketFactory.trustManager)
                    .build()
            )
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create()
    }

    @Provides
    fun provideDatabase(context: Application): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "Database").build()
    }

    @Provides
    fun provideWorkerDao(db: AppDatabase): WorkerDao {
        return db.workerDao()
    }
}