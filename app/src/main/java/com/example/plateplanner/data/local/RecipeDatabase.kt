package com.example.plateplanner.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.plateplanner.data.Converters

@Database(entities = [LocalRecipe::class], version = 1, exportSchema = false)

@TypeConverters(Converters::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

}