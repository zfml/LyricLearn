package com.zfml.lyriclearn.presentation.songDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.zfml.lyriclearn.domain.model.Song
import com.zfml.lyriclearn.presentation.DetailSongTopAppBar

@Composable
@Destination()
fun SongDetailScreen(
    navigator: DestinationsNavigator,
    id: Int
) {
    val viewModel: SongDetailViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()


    Scaffold(
        topBar = {
            DetailSongTopAppBar(
                song = uiState.song,
                onFavoriteClicked = {viewModel.updateFavoriteStatus(it.id,!it.isFavorite)},
                onBack = {
                    navigator.popBackStack()
                }
            )
        },
        content = {padding ->
            Column (
                modifier = Modifier.padding(paddingValues = padding)
            ){
                SongDetailContent(uiState)
            }
        }
    )


}

@Composable
fun SongDetailContent(
    uiState: SongDetailUiState
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Text(
            text = uiState.song.title,
            fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
        )
        Text(
            text = " - Song by ${uiState.song.singer}",
            fontWeight = FontWeight.Light,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
        )
        Text(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(8.dp)
            ,
            text = uiState.song.lyrics,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Left
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SongDetailContentPreview() {
    SongDetailContent(
        uiState = SongDetailUiState(
            Song(
                id = 0,
                title = "Dont ask me",
                lyrics = "You're the light, you're the night\n" +
                        "You're the colour of my blood\n" +
                        "You're the cure, you're the pain\n" +
                        "You're the only thing I wanna touch\n" +
                        "Never knew that it could mean so much, so much\n" +
                        "You're the fear, I don't care\n" +
                        "'Cause I've never been so high\n" +
                        "Follow me through the dark\n" +
                        "Let me take you past our satellites\n" +
                        "You can see the world you brought to life, to life\n" +
                        "So love me like you do, la-la-love me like you do\n" +
                        "Love me like you do, la-la-love me like you do\n" +
                        "Touch me like you do, ta-ta-touch me like you do\n" +
                        "What are you waiting for?\n" +
                        "Fading in, fading out\n" +
                        "On the edge of paradise\n" +
                        "Every inch of your skin is a Holy Grail I've got to find\n" +
                        "Only you can set my heart on fire, on fire\n" +
                        "Yeah, I'll let you set the pace\n" +
                        "'Cause I'm not thinking straight\n" +
                        "My head's spinning around, I can't see clear no more\n" +
                        "What are you waiting for?\n" +
                        "Love me like you do, la-la-love me like you do (like you do)\n" +
                        "Love me like you do, la-la-love me like you do\n" +
                        "Touch me like you do, ta-ta-touch me like you do\n" +
                        "What are you waiting for?\n" +
                        "Love me like you do, la-la-love me like you do (like you do)\n" +
                        "Love me like you do, la-la-love me like you do (ye-yeah)\n" +
                        "Touch me like you do, ta-ta-touch me like you do\n" +
                        "What are you waiting for?\n" +
                        "I'll let you set the pace\n" +
                        "'Cause I'm not thinking straight\n" +
                        "My head's spinning around, I can't see clear no more\n" +
                        "What are you waiting for?\n" +
                        "Love me like you do, la-la-love me like you do (like you do)\n" +
                        "Love me like you do, la-la-love me like you do (ye-yeah)\n" +
                        "Touch me like you do, ta-ta-touch me like you do\n" +
                        "What are you waiting for?\n" +
                        "Love me like you do, la-la-love me like you do (like you do)\n" +
                        "Love me like you do, la-la-love me like you do (whoa)\n" +
                        "Touch me like you do, ta-ta-touch me like you do (ah)\n" +
                        "What are you waiting for?",
                singer = "Celion",
                isFavorite = true
            )
        )
    )
}