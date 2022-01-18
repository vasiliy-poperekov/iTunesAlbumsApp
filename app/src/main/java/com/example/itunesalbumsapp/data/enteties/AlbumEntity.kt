package com.example.itunesalbumsapp.data.enteties

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = AlbumContract.TABLE_NAME)
data class AlbumEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = AlbumContract.Column.ID)
    val id: Int,

    @ColumnInfo(name = AlbumContract.Column.NAME)
    val name: String,

    @ColumnInfo(name = AlbumContract.Column.ARTIST)
    val artist: String,

    @ColumnInfo(name = AlbumContract.Column.COVER_URL)
    val coverUrl: String,

    @ColumnInfo(name = AlbumContract.Column.RELEASE_DATE)
    val releaseDate: String,

    @ColumnInfo(name = AlbumContract.Column.GENRE)
    val genre: String,

    @ColumnInfo(name = AlbumContract.Column.TRACK_COUNT)
    val trackCount: Int
)

object AlbumContract {
    const val TABLE_NAME = "album"

    object Column {
        const val ID = "id"
        const val NAME = "name"
        const val ARTIST = "artist"
        const val COVER_URL = "cover_url"
        const val RELEASE_DATE = "release_date"
        const val GENRE = "genre"
        const val TRACK_COUNT = "track_count"
    }
}