package com.example.plateplanner.ui.discover

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plateplanner.R
import com.example.plateplanner.data.RecipeRepository
import com.example.plateplanner.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

data class DiscoverUiState(
    val recipes: List<Recipe> = emptyList(),
    val userMessage: Int? = null

)

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _recipes =  recipeRepository.getRecipesStream()
        .catch { cause ->
            handleError(cause)
            emit(emptyList())
        }




    private val _userMessage: MutableStateFlow<Int?> = MutableStateFlow(null)


    val uiState: StateFlow<DiscoverUiState> = combine(_userMessage, _recipes) { userMessage, recipes ->
        DiscoverUiState(
            recipes = recipes,
            userMessage = userMessage

        )
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = DiscoverUiState()
        )

    private fun handleError(cause: Throwable) {
        // Handle the error, for example, log it or emit a default value
        Timber.e(cause, "Error loading data")
        _userMessage.value = R.string.error_recipes
        // Additional error handling logic if needed
    }


}