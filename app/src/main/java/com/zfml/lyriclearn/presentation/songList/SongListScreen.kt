package com.zfml.lyriclearn.presentation.songList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.zfml.lyriclearn.domain.model.Song
import com.zfml.lyriclearn.presentation.SongListAppBar
import com.zfml.lyriclearn.presentation.destinations.SongDetailScreenDestination
import com.zfml.lyriclearn.presentation.destinations.SongFavoriteScreenDestination
import com.zfml.lyriclearn.presentation.destinations.SongListSearchScreenDestination
import com.zfml.lyriclearn.presentation.songList.components.SongCard

@Composable
@Destination(
    start = true
)
fun SongListScreen(
    viewModel: SongListViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            SongListAppBar(
                onSearchClicked = {navigator.navigate(SongListSearchScreenDestination())},
                onClickedFavorite = {navigator.navigate(SongFavoriteScreenDestination())}
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier.padding(padding)
            ) {
                SongListContent(
                    uiState = uiState,
                    onClicked = {navigator.navigate(SongDetailScreenDestination(it))},
                    onFavoriteClicked = {viewModel.updateFavoriteStatus(it.id,!it.isFavorite)}
                )
            }
        }
    )

}

@Composable
fun SongListContent(
    uiState: SongListUiState,
    onClicked: (Int) -> Unit,
    onFavoriteClicked:(Song) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyColumn() {
            items(
                uiState.songs,
                key = {it.id}
            ) {
                SongCard(
                    song = it,
                    onClicked = onClicked,
                    onFavoriteClicked = onFavoriteClicked
                )
            }
        }
    }
}