package io.github.cloudyhug.cloudycook.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_content")
data class RecipeContentEntry(
  @PrimaryKey val id: Int,
  @ColumnInfo(name = "recipe_id") val recipe_id: Int,
  @ColumnInfo(name = "ingredient_id") val ingredient_id: Int,
  @ColumnInfo(name = "quantity") val quantity: String
)