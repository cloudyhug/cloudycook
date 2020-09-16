package io.github.cloudyhug.cloudycook

object IngredientCategory {
  val Vegetables = 0
  val Meat = 1
  val Fish = 2
  val Eggs = 3
  val Dairy = 4
  val Sauces = 5
  val Spices = 6
  val Carbohydrates = 7
  val Liquids = 8
  
  fun toIcon(category: Int): Int {
    return when (category) {
      Vegetables -> R.drawable.ic_fruit_cherries
      Meat -> R.drawable.ic_food_steak
      Fish -> R.drawable.ic_fish
      Eggs -> R.drawable.ic_egg
      Dairy -> R.drawable.ic_cheese
      Sauces -> R.drawable.ic_soy_sauce
      Spices -> R.drawable.ic_shaker
      Carbohydrates -> R.drawable.ic_barley
      else -> R.drawable.ic_beer
    }
  }
}