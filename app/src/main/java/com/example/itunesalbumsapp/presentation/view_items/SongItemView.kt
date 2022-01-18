package com.example.itunesalbumsapp.presentation.view_items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.itunesalbumsapp.domain.enteties.Song
import com.example.itunesalbumsapp.presentation.utils.LayoutsHelper.LITTLE_MARGIN

@Composable
fun SongItemView(song: Song) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = song.name,
            modifier = Modifier
                .background(Color.LightGray)
                .padding(LITTLE_MARGIN)
        )
    }
}