package io.github.cloudyhug.cloudycook.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredient")
data class IngredientEntry(
  @PrimaryKey val id: Int,
  @ColumnInfo(name = "name") val name: String,
  @ColumnInfo(name = "category") val category: Int
)