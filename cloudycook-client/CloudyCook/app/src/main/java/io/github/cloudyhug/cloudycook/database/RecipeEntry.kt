package io.github.cloudyhug.cloudycook.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class RecipeEntry(
  @PrimaryKey val id: Int,
  @ColumnInfo(name = "group") val group: Int,
  @ColumnInfo(name = "name") val name: String,
  @ColumnInfo(name = "difficulty") val difficulty: Int
)