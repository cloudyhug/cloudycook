package io.github.cloudyhug.cloudycook

import io.github.cloudyhug.cloudycook.database.IngredientEntry
import io.github.cloudyhug.cloudycook.database.RecipeEntry

object ViewRecipeData {
  var recipe: RecipeEntry? = null
  var ingredients: List<Pair<IngredientEntry, String>>? = null
  var steps: List<String>? = null
}