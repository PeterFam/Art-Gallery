package com.peterfam.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "arts")
data class Art(
    var name: String,
    var artistName: String,
    var year: Int,
    var imageURL: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int
)
