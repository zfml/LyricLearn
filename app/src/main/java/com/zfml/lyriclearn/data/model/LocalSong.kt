package com.zfml.lyriclearn.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "localSong")
data class LocalSong(
    @PrimaryKey
    val id: Int,
    val title: String,
    val lyrics: String,
    val singer: String,
    val isFavorite: Boolean = false
)