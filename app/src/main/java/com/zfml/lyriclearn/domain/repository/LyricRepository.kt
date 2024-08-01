package com.zfml.lyriclearn.domain.repository

import com.zfml.lyriclearn.domain.model.Song
import kotlinx.coroutines.flow.Flow

interface LyricRepository {
    fun getAllSongs(): Flow<List<Song>>

    fun getSongById(songId: Int): Flow<Song?>

    suspend fun insertSongs(songs: List<Song>)

    fun  getAllFavoriteSongs(): Flow<List<Song>>

    suspend fun updateFavoriteStatus(id:Int, isFavorite: Boolean)
}