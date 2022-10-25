package com.febrian.networkboundresources.di

import android.app.Application
import androidx.room.Room
import com.febrian.networkboundresources.api.ApiService
import com.febrian.networkboundresources.data.database.RestaurantDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideAPi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : RestaurantDatabase =
        Room.databaseBuilder(app, RestaurantDatabase::class.java, "restaurant")
            .build()
}