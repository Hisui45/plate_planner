package com.example.plateplanner.data

import com.example.plateplanner.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    fun getRecipesStream(): Flow<List<Recipe>>

    suspend fun getRecipes(forceUpdate: Boolean = false): List<Recipe>

//    suspend fun refresh()

    fun getRecipeStream(recipeId: String): Flow<Recipe?>

    suspend fun getRecipe(choreId: String, forceUpdate: Boolean = false): Recipe?

    suspend fun refreshRecipe(choreId: String)

    suspend fun createRecipe(
        title: String
    ): String

//    suspend fun createRecipe(title: String): String

    suspend fun updateRecipe(
        choreId: String, title: String
    )

    suspend fun favoriteRecipe(choreId: String)

    suspend fun unFavoriteRecipe(choreId: String)

    suspend fun deleteAllRecipes()

    suspend fun deleteRecipe(choreId: String)

}
