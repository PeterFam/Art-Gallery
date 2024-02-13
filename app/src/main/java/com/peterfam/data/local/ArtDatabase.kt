package com.peterfam.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.peterfam.data.model.Art

@Database(entities = [Art::class], version = 1)
abstract class ArtDatabase : RoomDatabase() {

    abstract fun artDao(): ArtDao

}