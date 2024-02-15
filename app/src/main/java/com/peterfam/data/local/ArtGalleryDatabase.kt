package com.peterfam.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.peterfam.data.local.model.Art

@Database(entities = [Art::class], version = 1)
abstract class ArtGalleryDatabase : RoomDatabase() {
    abstract fun artDao(): ArtGalleryDao
}