package com.zfml.lyriclearn.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zfml.lyriclearn.data.model.LocalSong
import com.zfml.lyriclearn.domain.model.Song
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {

    @Query("SELECT * FROM localSong")
    fun getAllSongs(): Flow<List<LocalSong>>

    @Query("SELECT * FROM localSong where id =:songId")
    fun getSongById(songId: Int): Flow<LocalSong?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(songs: List<LocalSong>)

    @Query("SELECT * FROM localSong WHERE isFavorite = 1")
    fun getFavoriteSongs(): Flow<List<Song>>

    @Query("UPDATE localSong SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean)

}