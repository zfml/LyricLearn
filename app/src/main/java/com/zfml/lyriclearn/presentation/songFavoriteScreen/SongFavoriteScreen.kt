package com.zfml.lyriclearn.presentation.songList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.zfml.lyriclearn.domain.model.Song
import com.zfml.lyriclearn.presentation.FavoriteSongsTopAppBar
import com.zfml.lyriclearn.presentation.SongListAppBar
import com.zfml.lyriclearn.presentation.destinations.SongDetailScreenDestination

import com.zfml.lyriclearn.presentation.songFavoriteScreen.SongFavoriteViewModel
import com.zfml.lyriclearn.presentation.songList.components.SongCard
import com.zfml.lyriclearn.presentation.songListSearchScreen.EmptyScreen

@Composable
@Destination()
fun SongFavoriteScreen(
    viewModel: SongFavoriteViewModel= hiltViewModel(),
    navigator: DestinationsNavigator,
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            FavoriteSongsTopAppBar (
                onBack = {navigator.popBackStack()}
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding)
            ) {

                if(uiState.songs.isNotEmpty()) {
                    SongListContent(
                        uiState = uiState,
                        onClicked = {navigator.navigate(SongDetailScreenDestination(it))},
                        onFavoriteClicked = {viewModel.updateFavoriteStatus(it.id,!it.isFavorite)}
                    )
                } else {
                    EmptyScreen()
                }

            }
        }
    )

}
