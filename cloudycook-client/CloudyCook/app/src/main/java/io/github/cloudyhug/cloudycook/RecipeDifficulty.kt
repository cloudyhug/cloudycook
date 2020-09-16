package io.github.cloudyhug.cloudycook

object RecipeDifficulty {
  val Easy = 0
  val Medium = 1
  val Hard = 2
  
  fun toString(recipeDifficulty: Int): String {
    return when (recipeDifficulty) {
      Easy -> "Easy"
      Medium -> "Medium"
      else -> "Hard"
    }
  }
}