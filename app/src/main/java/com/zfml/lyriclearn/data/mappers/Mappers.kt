package com.zfml.lyriclearn.data.mappers

import com.zfml.lyriclearn.data.model.LocalSong
import com.zfml.lyriclearn.domain.model.Song


fun LocalSong.toSong(): Song = Song(
    id = id,
    title = title,
    lyrics = lyrics,
    singer = singer,
    isFavorite = isFavorite
)

fun Song.toLocalSong(): LocalSong = LocalSong(
    id = id,
    title = title,
    lyrics = lyrics,
    singer = singer
)