package com.zfml.lyriclearn.domain.model

data class Artist(
    val id: Int,
    val name: String,
    val songs: List<Song>
)