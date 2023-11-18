package com.example.plateplanner


import com.example.plateplanner.data.local.LocalRecipe
import com.example.plateplanner.model.Recipe

    // External to local
    fun Recipe.toLocalRecipe() = LocalRecipe(
            id = id,
            title = title
    )

    fun List<Recipe>.toLocalRecipe() = map(Recipe::toLocalRecipe)

    // Local to External
    fun LocalRecipe.toExternalRecipe() = Recipe(
//        id = id,
//        title = title,
//        rooms = rooms,
//        routineInfo = routineInfo,
//        isTimeModeActive = isTimeModeActive,
//        timerModeValue = timerModeValue,
//        timerOption = timerOption,
//        isBankModeActive = isBankModeActive,
//        bankModeValue = bankModeValue,
//        isFavorite = isFavorite
    )

    // Note: JvmName is used to provide a unique name for each extension function with the same name.
// Without this, type erasure will cause compiler errors because these methods will have the same
// signature on the JVM.
    @JvmName("localRecipeToExternalRecipe")
    fun List<LocalRecipe>.toExternalRecipe() = map(LocalRecipe::toExternalRecipe)

    // Network to Local
//    fun NetworkRecipe.toLocalRecipe() = LocalRecipe(
//        id = id,
//        title = title,
//        rooms = rooms,
//        routineInfo = routineInfo,
//        isTimeModeActive = isTimeModeActive,
//        timerModeValue = timerModeValue,
//        timerOption = timerOption,
//        isBankModeActive = isBankModeActive,
//        bankModeValue = bankModeValue,
//        isFavorite = isFavorite
//    )

//    @JvmName("networkRecipeToLocalRecipe")
//    fun List<NetworkRecipe>.toLocalRecipe() = map(NetworkRecipe::toLocalRecipe)

    // External to Network
//    fun Recipe.toNetworkRecipe() = toLocalRecipe().toNetworkRecipe()
//
//    @JvmName("externalRecipeToNetworkRecipe")
//    fun List<Recipe>.toNetworkRecipe() = map(Recipe::toNetworkRecipe)

// Local to Network
//
//    //Recipe
//    fun LocalRecipe.toNetworkRecipe() = NetworkRecipe(
//        id = id,
//        title = title,
//        rooms = rooms,
//        routineInfo = routineInfo,
//        isTimeModeActive = isTimeModeActive,
//        timerModeValue = timerModeValue,
//        timerOption = timerOption,
//        isBankModeActive = isBankModeActive,
//        bankModeValue = bankModeValue,
//        isFavorite = isFavorite
//    )
//
//    fun List<LocalRecipe>.toNetworkRecipe() = map(LocalRecipe::toNetworkRecipe)

// Network to External
//    /**
//     * Recipe
//     */
//    fun NetworkRecipe.toExternalRecipe() = toLocalRecipe().toExternalRecipe()
//
//    @JvmName("networkRecipeToExternalRecipe")
//    fun List<NetworkRecipe>.toExternalRecipe() = map(NetworkRecipe::toExternalRecipe)

