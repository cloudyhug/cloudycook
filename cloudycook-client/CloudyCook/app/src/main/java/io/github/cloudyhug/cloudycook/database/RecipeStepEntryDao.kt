package io.github.cloudyhug.cloudycook.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeStepEntryDao {
  @Insert
  fun insertAll(vararg steps: RecipeStepEntry)
  
  @Query("SELECT * FROM step WHERE recipe_id = :id")
  fun findByRecipeId(id: Int): List<RecipeStepEntry>
  
  @Query("DELETE FROM step")
  fun emptySteps()
}