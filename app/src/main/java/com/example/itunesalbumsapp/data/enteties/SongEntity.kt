package com.example.itunesalbumsapp.data.enteties

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = SongContract.TABLE_NAME)
data class SongEntity(
    @ColumnInfo(name = SongContract.Column.COLLECTION_ID)
    val collectionId: Int,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = SongContract.Column.TRACK_ID)
    val trackId: Int,

    @ColumnInfo(name = SongContract.Column.TRACK_NAME)
    val trackName: String
)

object SongContract {
    const val TABLE_NAME = "song"

    object Column {
        const val COLLECTION_ID = "collection_id"
        const val TRACK_ID = "track_id"
        const val TRACK_NAME = "track_name"
    }
}