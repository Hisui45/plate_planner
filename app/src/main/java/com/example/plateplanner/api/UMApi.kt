package com.example.plateplanner.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

//public interface RecipeApi {
//    @Headers(
//        "Accept: application/json"
//    )
//    @GET("users/{id}")
//    abstract fun getUserById(@Path("id") id: String): Call<UserModel?>?

//    @GET("current.json")
//    suspend fun getResponse(
//        @Query("key") apiKey:String,
//        @Query("q") location:String,
//    ):WeatherResponse
//}