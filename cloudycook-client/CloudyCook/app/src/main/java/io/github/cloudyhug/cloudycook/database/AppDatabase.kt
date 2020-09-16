package io.github.cloudyhug.cloudycook.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
  entities = arrayOf(
    RecipeEntry::class,
    IngredientEntry::class,
    RecipeContentEntry::class,
    RecipeStepEntry::class,
    Counter::class
  ),
  version = 1
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun recipeEntryDao(): RecipeEntryDao
  abstract fun ingredientEntryDao() : IngredientEntryDao
  abstract fun recipeContentEntryDao() : RecipeContentEntryDao
  abstract fun recipeStepEntryDao() : RecipeStepEntryDao
  abstract fun counterDao() : CounterDao
}