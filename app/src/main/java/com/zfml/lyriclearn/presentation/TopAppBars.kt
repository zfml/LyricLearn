package com.zfml.lyriclearn.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.zfml.lyriclearn.R
import com.zfml.lyriclearn.domain.model.Song

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongListAppBar(
    onSearchClicked: () -> Unit,
    onClickedFavorite: () -> Unit,
) {

    TopAppBar(
        title = {
           Text(
               text = "Songs"
           )
        },
        actions = {
            IconButton(onClick = {onSearchClicked()}) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            }
            Menu(onClickedFavorite = onClickedFavorite)

        },
        modifier = Modifier.fillMaxWidth()
    )

}
@Composable
private fun Menu(
  onClickedFavorite:() -> Unit
) {
    TopAppBarDropDownMenu(
        iconContent = {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Favorite Icon"
            )
        },
        content = {closeMenu ->
            DropdownMenuItem(
                text = {
                Text(text = " Favorite Songs") },

                onClick = { onClickedFavorite(); closeMenu()}
            )


        }
    )
}
@Composable
private fun TopAppBarDropDownMenu(
    iconContent: @Composable () -> Unit,
    content: @Composable ColumnScope.(() -> Unit) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.wrapContentSize(align = Alignment.TopEnd)) {
        IconButton(onClick = { expanded = !expanded}) {
            iconContent()
        }
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false}
    ) {
        content{expanded = !expanded}
    }


}
@Composable
@Preview(showBackground = true)
fun SongListAppBarPreview() {
    SongListAppBar(
        onSearchClicked = {},
        onClickedFavorite = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongListSearchTopAppBar(
    searchQuery: String,
    onSearchChanged:(String) -> Unit,
    onClear:() -> Unit
) {

    val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = Color.Transparent,
        unfocusedBorderColor = Color.Transparent,
    )


    TopAppBar(
        title = {
            OutlinedTextField(
                value = searchQuery ,
                onValueChange = onSearchChanged,
                placeholder = {
                    Text(text = "Search With Singer or Lyric")
                },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                },
                trailingIcon = {
                    IconButton(onClick = onClear) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear Icon")
                    }
                },
                colors = textFieldColors
            )
        },

        )
}

@Preview
@Composable
fun SongListSearchTopAppBarPreview() {
    SongListSearchTopAppBar(
        onClear = {},
        onSearchChanged = {

        },
        searchQuery = ""
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailSongTopAppBar(
    song: Song,
    onBack:() -> Unit,
    onFavoriteClicked: (Song) -> Unit
) {
    TopAppBar(
        title = { Text(text = "") },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back_icon"
                )
            }

        },
        actions = {
            if(song.isFavorite) {
                IconButton(onClick = {onFavoriteClicked(song) }) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "favorite Icon",
                        tint = Color.Gray
                    )
                }
            } else {
                IconButton(onClick = {onFavoriteClicked(song) }) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "favorite Icon",
                        tint = Color.Gray
                    )
                }
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteSongsTopAppBar(
    onBack: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "Favorite Songs") },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back_icon"
                )
            }

        },
        modifier = Modifier.fillMaxWidth()
    )
}
@Preview
@Composable
fun FavoriteSongsTopAppBarPreview() {
    FavoriteSongsTopAppBar {

    }
}


@Composable
@Preview(showBackground = true)
fun DetailSongTopAppBar() {
  DetailSongTopAppBar (
      song = Song(id = -1,"","","",false),
      onBack = {},
      onFavoriteClicked = {}
  )
}
