package io.github.cloudyhug.cloudycook.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IngredientEntryDao {
  @Query("SELECT * FROM ingredient")
  fun getAll(): List<IngredientEntry>
  
  @Query("SELECT * FROM ingredient WHERE id = :id")
  fun findById(id: Int): IngredientEntry
  
  @Query("DELETE FROM ingredient")
  fun emptyIngredients()
  
  @Insert
  fun insertAll(vararg ingredientEntries: IngredientEntry)
}