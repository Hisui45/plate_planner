package com.example.plateplanner.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    /**
     * Observes list of recipes.
     *
     * @return all recipes.
     */
    @Query("SELECT * FROM recipe")
    fun observeAllRecipes(): Flow<List<LocalRecipe>>

    /**
     * Observes a single recipe.
     *
     * @param recipeId the recipe id.
     * @return the recipe with recipeId.
     */
    @Query("SELECT * FROM recipe WHERE id = :recipeId")
    fun observeRecipeById(recipeId: String): Flow<LocalRecipe>

    /**
     * Select all recipes from the recipes table.
     *
     * @return all recipes.
     */
    @Query("SELECT * FROM recipe")
    suspend fun getAllRecipes(): List<LocalRecipe>

    /**
     * Select a recipe by id.
     *
     * @param recipeId the recipe id.
     * @return the recipe with recipeId.
     */
    @Query("SELECT * FROM recipe WHERE id = :recipeId")
    suspend fun getRecipeById(recipeId: String): LocalRecipe?

    /**
     * Insert or update a recipe in the database. If a recipe already exists, replace it.
     *
     * @param recipe the recipe to be inserted or updated.
     */
    @Upsert
    suspend fun upsertRecipe(recipe: LocalRecipe)

    /**
     * Insert or update recipes in the database. If a recipe already exists, replace it.
     *
     * @param recipes the recipes to be inserted or updated.
     */

    @Insert
    fun insertAllRecipes(recipes: List<LocalRecipe>)

    @Upsert
    suspend fun upsertAllRecipes(recipes: List<LocalRecipe>)

//    /**
//     * Update the favorite status of a recipe
//     *
//     * @param recipeId id of the recipe
//     * @param favorite status to be updated
//     */
//
//    @Query("UPDATE recipe SET isFavorite = :favorite WHERE id = :recipeId")
//    suspend fun updateFavorite(recipeId: String, favorite: Boolean)

    /**
     * Delete a recipe by id.
     *
     * @return the number of recipes deleted. This should always be 1.
     */
    @Query("DELETE FROM recipe WHERE id = :recipeId")
    suspend fun deleteRecipeById(recipeId: String): Int

    /**
     * Delete all recipes.
     */
    @Query("DELETE FROM recipe")
    suspend fun deleteAllRecipes()

//    /**
//     * Delete all completed recipes from the table.
//     *
//     * @return the number of recipes deleted.
//     */
//    @Query("DELETE FROM recipe WHERE isCompleted = 1")
//    suspend fun deleteCompleted(): Int
    
}