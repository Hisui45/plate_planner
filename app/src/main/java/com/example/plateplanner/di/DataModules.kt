package com.example.plateplanner.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.plateplanner.R
import com.example.plateplanner.data.RecipeRepository
import com.example.plateplanner.data.DefaultRecipeRepository
import com.example.plateplanner.data.local.LocalRecipe
import com.example.plateplanner.data.local.RecipeDao
import com.example.plateplanner.data.local.RecipeDatabase
import com.example.plateplanner.model.Recipe
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindChoreRepository(repository: DefaultRecipeRepository): RecipeRepository
}

//@Module
//@InstallIn(SingletonComponent::class)
//abstract class DataSourceModule {
//
//    @Singleton
//    @Binds
//    abstract fun bindNetworkDataSource(dataSource: RecipeNetworkDataSource): NetworkDataSource
//}

/**
 * TODO: have room and choreItem lists store arrays of ids priority : 1
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): RecipeDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            RecipeDatabase::class.java,
            "planner.db"
        )
            .addCallback(PrepopulateRoomCallback(context))
            .build()
    }

//    private var dbCallback: RoomDatabase.Callback = object : RoomDatabase.Callback() {
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            Executors.newSingleThreadScheduledExecutor()
//                .execute({ provideDataBase(context = ).yourDAO().insertData(yourDataList) })
//        }
//    }

    @Provides
    fun provideRecipeDao(database: RecipeDatabase): RecipeDao = database.recipeDao()


    class PrepopulateRoomCallback(private val context: Context) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            CoroutineScope(Dispatchers.IO).launch {
                prePopulateRecipes(context)
            }
        }
    }
    suspend fun prePopulateRecipes(context: Context) {
        try {
            val recipeDao = provideDataBase(context).recipeDao()
            val userList: JSONArray =
                context.resources.openRawResource(R.raw.recipes).bufferedReader().use {
                    JSONArray(it.readText())
                }
            val recipeList: MutableList<LocalRecipe> = mutableListOf()
            userList.takeIf { it.length() > 0 }?.let { list ->
                for (index in 0 until list.length()) {
                    val recipeObj = list.getJSONObject(index)

                    recipeList.add(
                        LocalRecipe(
                            id = recipeObj.getString("id"),
                            title = recipeObj.getString("")
                        )
                    )

//                    recipeDao.insertUser(
//                        Recipe(
//                            recipeObj.getInt("userId"),
//                            recipeObj.getString("userName")
//                        )
//                    )

                }
                recipeDao.insertAllRecipes(recipeList)
                Log.e("User App", "successfully pre-populated users into database")
            }
        } catch (exception: Exception) {
            Log.e(
                "User App",
                exception.localizedMessage ?: "failed to pre-populate users into database"
            )
        }
    }


}