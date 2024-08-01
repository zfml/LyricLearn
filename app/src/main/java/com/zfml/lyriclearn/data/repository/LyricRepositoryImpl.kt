package com.zfml.lyriclearn.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zfml.lyriclearn.data.local.SongDao
import com.zfml.lyriclearn.data.mappers.toLocalSong
import com.zfml.lyriclearn.data.mappers.toSong
import com.zfml.lyriclearn.domain.model.Song
import com.zfml.lyriclearn.domain.repository.LyricRepository
import com.zfml.lyriclearn.util.getJsonDataFromAsset
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class LyricRepositoryImpl @Inject constructor(
    private val context: Context,
    private val songDao: SongDao
) : LyricRepository {

    override fun getAllSongs(): Flow<List<Song>> {
        return songDao.getAllSongs().map { it.map { it.toSong() } }
    }

    override fun getSongById(songId: Int): Flow<Song?> {
        return songDao.getSongById(songId).map { it?.toSong() }
    }

    override suspend fun insertSongs(songs: List<Song>) {
        songDao.insertAll(songs.map { it.toLocalSong() })
    }

    override fun getAllFavoriteSongs(): Flow<List<Song>> {
        return songDao.getFavoriteSongs()
    }

    override suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean) {
        songDao.updateFavoriteStatus(id,isFavorite)
    }

    private suspend fun loadSongsFromJson(): List<Song> = withContext(Dispatchers.IO) {
        val jsonFileString = getJsonDataFromAsset(context, "lyrics.json")
        val listType = object : TypeToken<List<Song>>() {}.type
        Gson().fromJson(jsonFileString, listType)
    }


}