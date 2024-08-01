package com.zfml.lyriclearn.presentation.songFavoriteScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.reflect.TypeToken
import com.zfml.lyriclearn.domain.model.Song
import com.zfml.lyriclearn.domain.repository.LyricRepository
import com.zfml.lyriclearn.presentation.songList.SongListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongFavoriteViewModel  @Inject constructor(
    private val lyricRepository: LyricRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SongListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            lyricRepository.getAllFavoriteSongs().collect {songs ->

                    _uiState.value = SongListUiState(songs)
                }

            }

        }


    fun updateFavoriteStatus(songId: Int,isFavorite: Boolean) {
        viewModelScope.launch {
            lyricRepository.updateFavoriteStatus(songId, isFavorite)
        }
    }

}