package com.zfml.lyriclearn.presentation.songDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zfml.lyriclearn.domain.model.Song
import com.zfml.lyriclearn.domain.repository.LyricRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongDetailViewModel @Inject constructor(
    private val lyricRepository: LyricRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {


    private val _uiState = MutableStateFlow(SongDetailUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            savedStateHandle.get<Int>("id")?.let { songId ->
              lyricRepository.getSongById(songId).collect {
                  it?.let {
                      _uiState.value = SongDetailUiState(it)
                  }
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
data class  SongDetailUiState(
    val song: Song = Song(id = -1, title = "", lyrics = "", singer = "", isFavorite = false)
)