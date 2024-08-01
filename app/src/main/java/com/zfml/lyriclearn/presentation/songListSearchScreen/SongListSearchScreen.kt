package com.zfml.lyriclearn.presentation.songListSearchScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.zfml.lyriclearn.presentation.SongListSearchTopAppBar
import com.zfml.lyriclearn.presentation.destinations.SongDetailScreenDestination
import com.zfml.lyriclearn.presentation.songList.SongListContent

@Destination
@Composable
fun SongListSearchScreen(
    viewModel: SongListSearchViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Scaffold(
        topBar = {
            SongListSearchTopAppBar(
                searchQuery = searchQuery,
                onSearchChanged = {viewModel.searchQuery.value = it},
                onClear = {navigator.popBackStack()}
            )
        },
        content = {padding ->
            Column(
                modifier = Modifier.padding(padding)
            ) {
                if(uiState.songs.isNotEmpty()) {
                    SongListContent(
                        uiState = uiState,
                        onClicked = {navigator.navigate(SongDetailScreenDestination(it))},
                        onFavoriteClicked = {viewModel.updateFavoriteStatus(it.id,!it.isFavorite)}
                    )
                }else {
                    EmptyScreen()
                }

            }
        }

    )

}
@Composable
fun EmptyScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "No Songs"
        )

    }
}