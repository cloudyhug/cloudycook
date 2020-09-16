package io.github.cloudyhug.cloudycook.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CounterDao {
  @Query("SELECT recipeCounter FROM counter WHERE id = 0")
  fun getRecipeCount(): Int
  
  @Query("UPDATE counter SET recipeCounter = (SELECT recipeCounter FROM counter WHERE id = 0) + 1")
  fun incrementRecipes()
  @Query("UPDATE counter SET recipeCounter = (SELECT recipeCounter FROM counter WHERE id = 0) - 1")
  fun decrementRecipes()
  
  @Query("UPDATE counter SET recipeCounter = (SELECT recipeCounter FROM counter WHERE id = 0) + :n")
  fun incrementRecipesBy(n: Int)
  @Query("UPDATE counter SET recipeCounter = (SELECT recipeCounter FROM counter WHERE id = 0) - :n")
  fun decrementRecipesBy(n: Int)
  
  @Query("SELECT recipeContentCounter FROM counter WHERE id = 0")
  fun getRecipeContentCount(): Int
  
  @Query("UPDATE counter SET recipeContentCounter = (SELECT recipeContentCounter FROM counter WHERE id = 0) + 1")
  fun incrementRecipeContents()
  @Query("UPDATE counter SET recipeContentCounter = (SELECT recipeContentCounter FROM counter WHERE id = 0) - 1")
  fun decrementRecipeContents()
  
  @Query("UPDATE counter SET recipeContentCounter = (SELECT recipeContentCounter FROM counter WHERE id = 0) + :n")
  fun incrementRecipeContentsBy(n: Int)
  @Query("UPDATE counter SET recipeContentCounter = (SELECT recipeContentCounter FROM counter WHERE id = 0) - :n")
  fun decrementRecipeContentsBy(n: Int)
  
  @Query("SELECT stepCounter FROM counter WHERE id = 0")
  fun getStepCount(): Int
  
  @Query("UPDATE counter SET stepCounter = (SELECT stepCounter FROM counter WHERE id = 0) + 1")
  fun incrementSteps()
  @Query("UPDATE counter SET stepCounter = (SELECT stepCounter FROM counter WHERE id = 0) - 1")
  fun decrementSteps()
  
  @Query("UPDATE counter SET stepCounter = (SELECT stepCounter FROM counter WHERE id = 0) + :n")
  fun incrementStepsBy(n: Int)
  @Query("UPDATE counter SET stepCounter = (SELECT stepCounter FROM counter WHERE id = 0) - :n")
  fun decrementStepsBy(n: Int)
  
  @Query("SELECT ingredientCounter FROM counter WHERE id = 0")
  fun getIngredientCount(): Int
  
  @Query("UPDATE counter SET ingredientCounter = (SELECT ingredientCounter FROM counter WHERE id = 0) + 1")
  fun incrementIngredients()
  @Query("UPDATE counter SET ingredientCounter = (SELECT ingredientCounter FROM counter WHERE id = 0) - 1")
  fun decrementIngredients()
  
  @Query("UPDATE counter SET ingredientCounter = (SELECT ingredientCounter FROM counter WHERE id = 0) + :n")
  fun incrementIngredientsBy(n: Int)
  @Query("UPDATE counter SET ingredientCounter = (SELECT ingredientCounter FROM counter WHERE id = 0) - :n")
  fun decrementIngredientsBy(n: Int)
  
  @Query("UPDATE counter SET recipeCounter = 0, ingredientCounter = 0, stepCounter = 0")
  fun reset()
}