package io.github.cloudyhug.cloudycook.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeEntryDao {
  @Query("SELECT * FROM recipe WHERE difficulty = :group")
  fun findByGroup(group: Int): List<RecipeEntry>
  
  @Query("SELECT * FROM recipe WHERE id = :id")
  fun findById(id: Int): RecipeEntry
  
  @Insert
  fun insert(recipe: RecipeEntry)
  
  @Insert
  fun insertAll(vararg recipes: RecipeEntry)
  
  @Query("DELETE FROM recipe")
  fun emptyRecipes()
}