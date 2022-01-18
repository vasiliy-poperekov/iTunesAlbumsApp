package com.example.itunesalbumsapp.presentation.view_items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.itunesalbumsapp.presentation.utils.LayoutsHelper.DEFAULT_MARGIN
import com.example.itunesalbumsapp.presentation.utils.LayoutsHelper.LITTLE_MARGIN
import com.example.itunesalbumsapp.domain.enteties.Album
import com.skydoves.landscapist.glide.GlideImage

@ExperimentalMaterialApi
@Composable
fun AlbumItemView(
    album: Album,
    onItemClick: (Album) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        onClick = { onItemClick(album) }
    ) {
        Row(
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(50.dp)
            ) {
                GlideImage(
                    imageModel = album.coverUrl,
                    modifier = Modifier
                        .size(64.dp)
                )
            }
            Spacer(modifier = Modifier.size(DEFAULT_MARGIN))
            Column {
                Text(
                    text = album.name,
                    fontSize = 24.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.size(LITTLE_MARGIN))
                Text(
                    text = album.artist,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}