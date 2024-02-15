package com.peterfam.di

import android.content.Context
import androidx.room.Room
import com.peterfam.data.local.ArtGalleryDao
import com.peterfam.data.local.ArtGalleryDatabase
import com.peterfam.data.remote.ApiService
import com.peterfam.utils.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesRetrofitAPI(): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Util.baseUrl)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        ArtGalleryDatabase::class.java,
        Util.DBName)

    @Singleton
    @Provides
    fun provideDao(database: ArtGalleryDatabase) : ArtGalleryDao = database.artDao()

}