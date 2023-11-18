package com.example.plateplanner.ui.discover

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plateplanner.R
//import com.example.plateplanner.api.RecipeApi
import com.example.plateplanner.data.RecipeRepository
import com.example.plateplanner.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import timber.log.Timber
import javax.inject.Inject

data class DiscoverUiState(
    val recipes: List<Recipe> = emptyList(),
    val userMessage: Int? = null

)

data class GptRequest(
    val prompt: String,
    val max_tokens: Int,
    val model: String
)

data class GptResponse(
    val choices: List<Choice>
)

data class Choice(
    val text: String
)

interface GptApi {
    @Headers(
        "Content-Type: application/json",
        "Authorization: https://api.umgpt.umich.edu/azure-openai-api/ptu"
    )
    @POST("/v1/completions")
    fun getCompletion(
        @Body requestBody: GptRequest
    ): Call<GptResponse>
}

class DiscoverViewModel : ViewModel() {

    var answer by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.umgpt.umich.edu/azure-openai-api/ptu/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(GptApi::class.java)

    fun sendRequest(question: String) {
        val request = GptRequest(
            prompt = question,
            max_tokens = 100,
            model = "text-davinci-003"
        )

        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            val call = api.getCompletion(request)
            val response = call.execute()
            isLoading = false

            if (response.isSuccessful) {
                val choice = response.body()?.choices?.get(0)
                viewModelScope.launch(Dispatchers.Main) {
                    choice?.text?.let {
                        answer = it
                    }
                }
            } else {
                viewModelScope.launch(Dispatchers.Main) {
                    answer = "Error: ${response.code()} - ${response.message()}"
                }
            }
        }
    }
}