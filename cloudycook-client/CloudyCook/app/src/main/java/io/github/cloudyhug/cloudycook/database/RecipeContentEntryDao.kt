package io.github.cloudyhug.cloudycook.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeContentEntryDao {
  @Query("SELECT * FROM recipe_content WHERE recipe_id = :recipeId")
  fun getIngredientsOfRecipe(recipeId: Int): List<RecipeContentEntry>
  
  @Query("DELETE FROM recipe_content")
  fun emptyRecipeContents()
  
  @Insert
  fun insertAll(vararg recipeContentEntries: RecipeContentEntry)
}