package com.example.plateplanner.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.plateplanner.data.RecipeRepository
import com.example.plateplanner.data.DefaultRecipeRepository
import com.example.plateplanner.data.local.RecipeDao
import com.example.plateplanner.data.local.RecipeDatabase
import com.example.plateplanner.data.network.NetworkDataSource
import com.example.plateplanner.data.network.RecipeNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindChoreRepository(repository: DefaultRecipeRepository): RecipeRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindNetworkDataSource(dataSource: RecipeNetworkDataSource): NetworkDataSource
}

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
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    Executors.newSingleThreadScheduledExecutor()
                        .execute {
//                            provideDataBase(context).recipeDao().insertAllWorkflows(dummyWorkflows)
                        }


                }
            }
            ).build()
    }

//    private var dbCallback: RoomDatabase.Callback = object : RoomDatabase.Callback() {
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            Executors.newSingleThreadScheduledExecutor()
//                .execute({ provideDataBase(context = ).yourDAO().insertData(yourDataList) })
//        }
//    }

    @Provides
    fun provideRecipeDao(database: RecipeDatabase): RecipeDao = database.recipeDao()




}