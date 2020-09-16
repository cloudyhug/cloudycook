package io.github.cloudyhug.cloudycook.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "counter")
data class Counter (
  @PrimaryKey val id: Int,
  @ColumnInfo(name = "recipeCounter") val recipeCounter: Int,
  @ColumnInfo(name = "recipeContentCounter") val recipeContentCounter: Int,
  @ColumnInfo(name = "stepCounter") val stepCounter: Int,
  @ColumnInfo(name = "ingredientCounter") val ingredientCounter: Int
)