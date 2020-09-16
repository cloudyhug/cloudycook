package io.github.cloudyhug.cloudycook

object RecipeGroup {
  val Starters = 0
  val MainCourses = 1
  val Desserts = 2
  
  fun toString(recipeGroup: Int): String {
    return when (recipeGroup) {
      Starters -> "Entrées"
      MainCourses -> "Plats de résistance"
      else -> "Desserts"
    }
  }
}