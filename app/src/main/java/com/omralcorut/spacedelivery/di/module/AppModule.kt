package com.omralcorut.spacedelivery.di.module

import android.content.Context
import androidx.room.Room
import com.omralcorut.spacedelivery.BuildConfig
import com.omralcorut.spacedelivery.api.StationApi
import com.omralcorut.spacedelivery.db.CacheDatabase
import com.omralcorut.spacedelivery.db.dao.ShipDao
import com.omralcorut.spacedelivery.db.dao.StationDao
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun cacheDatabase(@ApplicationContext context: Context): CacheDatabase {
        return Room
            .databaseBuilder(context, CacheDatabase::class.java, "spacedelivery.db")
            .build()
    }

    @Provides
    fun provideShipDao(database: CacheDatabase): ShipDao = database.shipDao()

    @Provides
    fun provideStationDao(database: CacheDatabase): StationDao = database.stationDao()


    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideStationApiService(okHttpClient: OkHttpClient): StationApi {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .client(okHttpClient)
            .build()
            .create(StationApi::class.java)
    }
}
