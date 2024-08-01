package com.zfml.lyriclearn.presentation.songList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.reflect.TypeToken
import com.zfml.lyriclearn.domain.model.Song
import com.zfml.lyriclearn.domain.repository.LyricRepository
import com.zfml.lyriclearn.util.JsonLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongListViewModel @Inject constructor(
    private val lyricRepository: LyricRepository,
    private val jsonLoader: JsonLoader
): ViewModel() {

    private val _uiState = MutableStateFlow(SongListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            lyricRepository.getAllSongs().collect {songs ->
                if(songs.isEmpty()) {
                    val listType = object : TypeToken<List<Song>>() {}.type
                    val newSongs = jsonLoader.loadFromJsonFile<Song>("lyrics.json", listType)
                    lyricRepository.insertSongs(newSongs)
                    _uiState.value = SongListUiState(newSongs)
                }else {
                    _uiState.value = SongListUiState(songs)
                }

             }

        }
    }

    fun updateFavoriteStatus(songId: Int,isFavorite: Boolean) {
        viewModelScope.launch {
            lyricRepository.updateFavoriteStatus(songId, isFavorite)
        }
    }

}
data class SongListUiState(
    val songs: List<Song> = emptyList()
)