package com.example.plateplanner.data

import com.example.plateplanner.data.local.RecipeDao
import com.example.plateplanner.di.ApplicationScope
import com.example.plateplanner.di.DefaultDispatcher
import com.example.plateplanner.model.Recipe
import com.example.plateplanner.toExternalRecipe
import com.example.plateplanner.toLocalRecipe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultRecipeRepository @Inject constructor(
//    private val networkDataSource: NetworkDataSource,
    private val localDataSource: RecipeDao,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationScope private val scope: CoroutineScope,
) : RecipeRepository {


    override suspend fun createRecipe(title: String): String {
        val recipeId = withContext(dispatcher) {
            UUID.randomUUID().toString()
        }
        val recipe = Recipe(
            title = title,
            id = recipeId,
        )
        localDataSource.upsertRecipe(recipe.toLocalRecipe())
//        saveRecipesToNetwork()
        return recipeId

    }

//    override suspend fun createRecipe(
//        title: String,
//    ): String {
//        // ID creation might be a complex operation so it's executed using the supplied
//        // coroutine dispatcher
//        val recipeId = withContext(dispatcher) {
//            UUID.randomUUID().toString()
//        }
//
//        val recipe = Recipe(
//            title = title,
//            id = recipeId,
//        )
//        localDataSource.upsertRecipe(recipe.toLocalRecipe())
////        saveRecipesToNetwork()
//        return recipeId
//    }

    override suspend fun updateRecipe(
        recipeId: String,
        title: String,
    ) {

        val recipe = getRecipe(recipeId)?.copy(
            title = title,
        ) ?: throw Exception("Recipe (id $recipeId) not found")

        localDataSource.upsertRecipe(recipe.toLocalRecipe())
//        saveRecipesToNetwork()
    }

    override suspend fun getRecipes(forceUpdate: Boolean): List<Recipe> {
        if (forceUpdate) {
//            refresh()
        }
        return withContext(dispatcher) {
            localDataSource.getAllRecipes().toExternalRecipe()
        }
    }


    override fun getRecipesStream(): Flow<List<Recipe>> {
        return localDataSource.observeAllRecipes().map { recipes ->
            withContext(dispatcher) {
                recipes.toExternalRecipe()
            }
        }
    }


    override suspend fun refreshRecipe(recipeId: String) {
//        refresh()
    }

    override fun getRecipeStream(recipeId: String): Flow<Recipe?> {
        return localDataSource.observeRecipeById(recipeId).map { it.toExternalRecipe() }
    }

    /**
     * Get a Recipe with the given ID. Will return null if the recipe cannot be found.
     *
     * @param recipeId - The ID of the recipe
     * @param forceUpdate - true if the recipe should be updated from the network data source first.
     */
    override suspend fun getRecipe(recipeId: String, forceUpdate: Boolean): Recipe? {
        if (forceUpdate) {
//            refresh()
        }
        return localDataSource.getRecipeById(recipeId)?.toExternalRecipe()
    }

    override suspend fun favoriteRecipe(recipeId: String) {
        localDataSource.updateFavorite(recipeId = recipeId, favorite = true)
//        saveRecipesToNetwork()
    }

    override suspend fun unFavoriteRecipe(recipeId: String) {
        localDataSource.updateFavorite(recipeId = recipeId, favorite = false)
//        saveRecipesToNetwork()
    }

    override suspend fun deleteAllRecipes() {
        localDataSource.deleteAllRecipes()
//        saveRecipesToNetwork()
    }

    override suspend fun deleteRecipe(recipeId: String) {
        localDataSource.deleteRecipeById(recipeId)
//        saveRecipesToNetwork()
    }

}