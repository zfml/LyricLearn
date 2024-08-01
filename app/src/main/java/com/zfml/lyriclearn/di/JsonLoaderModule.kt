package com.zfml.lyriclearn.di

import android.app.Application
import android.content.Context
import com.zfml.lyriclearn.util.JsonLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object JsonLoaderModule {

    @Provides
    @Singleton
    fun provideJsonLoader(app: Application): JsonLoader {
        return JsonLoader(app)
    }

}