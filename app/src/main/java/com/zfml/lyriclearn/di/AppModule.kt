package com.zfml.lyriclearn.di

import android.app.Application
import androidx.room.Room
import com.zfml.lyriclearn.data.local.SongDao
import com.zfml.lyriclearn.data.local.SongDatabase
import com.zfml.lyriclearn.data.repository.LyricRepositoryImpl
import com.zfml.lyriclearn.domain.model.Song
import com.zfml.lyriclearn.domain.repository.LyricRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): SongDatabase {
        return Room.databaseBuilder(
            app,
            SongDatabase::class.java,
            "song.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSongDao(db: SongDatabase): SongDao {
        return db.songDao
    }


    @Provides
    @Singleton
    fun provideLyricRepository(app: Application,songDao: SongDao): LyricRepository {
        return LyricRepositoryImpl(app,songDao)
    }




}