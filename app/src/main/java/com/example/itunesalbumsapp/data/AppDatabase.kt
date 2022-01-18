package com.example.itunesalbumsapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.itunesalbumsapp.data.AppDatabase.Companion.DB_VERSION
import com.example.itunesalbumsapp.data.daos.AlbumDao
import com.example.itunesalbumsapp.data.daos.SongDao
import com.example.itunesalbumsapp.data.enteties.AlbumEntity
import com.example.itunesalbumsapp.data.enteties.SongEntity

@Database(
    entities = [
        AlbumEntity::class,
        SongEntity::class
    ], version = DB_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAlbumDao(): AlbumDao
    abstract fun getSongDao(): SongDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "database"
    }
}