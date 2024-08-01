package com.zfml.lyriclearn.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zfml.lyriclearn.data.model.LocalSong

@Database(
    entities = [LocalSong::class],
    version = 1
)
abstract class SongDatabase: RoomDatabase() {
    abstract val songDao: SongDao
}