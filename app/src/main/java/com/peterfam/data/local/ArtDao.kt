package com.peterfam.data.local

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.peterfam.data.local.model.Art

interface ArtDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArt(art: Art)
    @Delete
    suspend fun deleteArt(art: Art)


    @Query("SELECT * FROM arts")
    fun observeArts(): LiveData<List<Art>>

}