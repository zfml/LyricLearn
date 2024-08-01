package com.zfml.lyriclearn.presentation.songListSearchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zfml.lyriclearn.domain.model.Song
import com.zfml.lyriclearn.domain.repository.LyricRepository
import com.zfml.lyriclearn.presentation.songList.SongListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongListSearchViewModel @Inject constructor(
  private val lyricRepository: LyricRepository
): ViewModel() {

    val searchQuery = MutableStateFlow("")
    val uiState = combine(searchQuery,lyricRepository.getAllSongs()) { searchQuery, songList ->
        val filterSongs = filterBySearch(searchQuery,songList)
            SongListUiState(songs = filterSongs)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        SongListUiState()
    )

    fun updateFavoriteStatus(songId: Int,isFavorite: Boolean) {
        viewModelScope.launch {
            lyricRepository.updateFavoriteStatus(songId, isFavorite)
        }
    }

}

private fun filterBySearch(searchQuery: String, songList: List<Song>): List<Song> {
    return songList.filter { it.title.contains(searchQuery, ignoreCase = true) || it.singer.contains(searchQuery, ignoreCase = true)  }
}