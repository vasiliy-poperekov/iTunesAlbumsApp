package com.example.itunesalbumsapp.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.itunesalbumsapp.data.enteties.SongContract
import com.example.itunesalbumsapp.data.enteties.SongEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSongsList(newsEntityList: List<SongEntity>)

    @Query("SELECT * FROM ${SongContract.TABLE_NAME} WHERE ${SongContract.Column.COLLECTION_ID} = :collectionId")
    fun getSongsList(collectionId: Int): Flow<List<SongEntity>?>
}