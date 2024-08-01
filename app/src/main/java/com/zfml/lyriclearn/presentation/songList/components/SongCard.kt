package com.zfml.lyriclearn.presentation.songList.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zfml.lyriclearn.R
import com.zfml.lyriclearn.domain.model.Song

@Composable
fun SongCard(
    song: Song,
    onClicked: (Int) -> Unit,
    onFavoriteClicked:(Song) -> Unit
) {


        Surface(
            modifier = Modifier
                .clip(Shapes().medium)
                .clickable {
                    onClicked(song.id)
                }
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 3.dp)
            ,
            tonalElevation = 2.dp,
            color = MaterialTheme.colorScheme.onPrimary
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = song.title,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                    )
                    Text(
                        text = song.lyrics,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        fontWeight = FontWeight.Light,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = "Song by ${song.singer}",
                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
                            fontWeight = FontWeight.Light,
                        )
                        IconButton(onClick = {
                            onFavoriteClicked(song)
                        }) {

                            if(song.isFavorite) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_favorite_24),
                                    contentDescription = "Favourite",
                                    tint = Color.Gray
                                )
                            } else {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_favorite_border_24),
                                    contentDescription = "Favourite",
                                    tint = Color.Gray
                                )
                            }

                        }
                    }

                }


            }
        }

}

@Composable
@Preview(showBackground = true)
fun SongCardPreview() {
    SongCard(
        song =
        Song(
            id = 0,
            title = "Love me like you do",
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
            singer = "Song by Ellie Goulding\n" +
                    "\n",
            isFavorite = false
        ),
        onClicked = {},
        onFavoriteClicked = {}
    )
}