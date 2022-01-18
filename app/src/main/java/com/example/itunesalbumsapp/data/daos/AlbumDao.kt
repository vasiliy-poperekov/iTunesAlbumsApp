package com.example.itunesalbumsapp.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.itunesalbumsapp.data.enteties.AlbumContract
import com.example.itunesalbumsapp.data.enteties.AlbumEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAlbumsList(newsEntityList: List<AlbumEntity>)

    @Query("SELECT * FROM ${AlbumContract.TABLE_NAME}")
    fun getAlbumsList(): Flow<List<AlbumEntity>?>

    @Query("SELECT * FROM ${AlbumContract.TABLE_NAME} WHERE ${AlbumContract.Column.ID} = :id")
    suspend fun getAlbumById(id: Int): AlbumEntity?

    @Query("DELETE FROM ${AlbumContract.TABLE_NAME}")
    suspend fun deleteAlbumsList()
}