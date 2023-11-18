package com.example.plateplanner.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "recipe"
)
data class LocalRecipe(
    @PrimaryKey val id: String,
    val title: String
)