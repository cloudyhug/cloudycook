package io.github.cloudyhug.cloudycook.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "step")
data class RecipeStepEntry(
  @PrimaryKey val id: Int,
  @ColumnInfo(name = "recipe_id") val recipe_id: Int,
  @ColumnInfo(name = "step_number") val step_number: Int,
  @ColumnInfo(name = "instruction") val instruction: String
)