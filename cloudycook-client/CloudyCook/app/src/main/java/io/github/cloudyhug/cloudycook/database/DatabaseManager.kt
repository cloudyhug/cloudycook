package io.github.cloudyhug.cloudycook.database

import com.google.gson.*

object DatabaseManager {
  fun populateFromJson(db: AppDatabase, jsonStr: String): Boolean {
    val counter = db.counterDao()
    val ingredients = db.ingredientEntryDao()
    val recipes = db.recipeEntryDao()
    val recipeContents = db.recipeContentEntryDao()
    val steps = db.recipeStepEntryDao()
    
    // reset db
    counter.reset()
    ingredients.emptyIngredients()
    recipes.emptyRecipes()
    recipeContents.emptyRecipeContents()
    steps.emptySteps()
    
    var json: JsonObject? = null
    try {
      json = JsonParser.parseString(jsonStr).asJsonObject
      
      // first add new ingredients
      val ingredientEntries =
        json["ingredients"].asJsonArray.mapIndexed { index, obj ->
          val ing = obj.asJsonObject
          IngredientEntry(index, ing["name"].asString, ing["category"].asInt)
        }.toTypedArray()
      ingredients.insertAll(*ingredientEntries)
      counter.incrementIngredientsBy(ingredientEntries.size)
  
      // then add recipes, their contents, and their steps
      
      var recipeContentId = 0
      var stepId = 0
      val recipeEntries = ArrayList<RecipeEntry>()
      val recipeContentEntries = ArrayList<RecipeContentEntry>()
      val recipeStepEntries = ArrayList<RecipeStepEntry>()
      
      // for every recipe
      json["recipes"].asJsonArray.forEachIndexed { index, obj ->
        val rec = obj.asJsonObject
        
        // add new recipe
        recipeEntries.add(
          RecipeEntry(index, rec["group"].asInt, rec["name"].asString, rec["difficulty"].asInt)
        )
        
        // for every ingredient
        rec["ingredients"].asJsonArray.forEach { ingObj ->
          val ing = ingObj.asJsonObject
          
          // add new ingredient in recipe
          recipeContentEntries.add(
            RecipeContentEntry(
              recipeContentId, index, ing["index"].asInt, ing["quantity"].asString
            )
          )
          recipeContentId += 1
        }
        
        // for every step
        rec["steps"].asJsonArray.forEachIndexed { stepIndex, stepObj ->
          // add new step in recipe
          recipeStepEntries.add(
            RecipeStepEntry(stepId, index, stepIndex, stepObj.asString)
          )
          stepId += 1
        }
      }
      recipes.insertAll(*recipeEntries.toTypedArray())
      recipeContents.insertAll(*recipeContentEntries.toTypedArray())
      steps.insertAll(*recipeStepEntries.toTypedArray())
      counter.incrementRecipesBy(recipeEntries.size)
      counter.incrementStepsBy(recipeStepEntries.size)
    } catch (e: Exception) {
      return false
    }
    return true
  }
  
  fun extractToJson(db: AppDatabase): String {
    val recipes = db.recipeEntryDao()
    val recipeContents = db.recipeContentEntryDao()
    val ingredients = db.ingredientEntryDao()
    val steps = db.recipeStepEntryDao()
    val ingredientsStr = ingredients.getAll().fold("", { acc, it ->
      acc + """{"name": "${it.name}", "category": "${it.category}"}"""
    })
    // TODO
    return ingredientsStr
  }
  
  val example = "{\n" +
          "  \"ingredients\": [\n" +
          "    {\n" +
          "      \"name\": \"Poireau\",\n" +
          "      \"category\": 0\n" +
          "    },\n" +
          "    {\n" +
          "      \"name\": \"Haddock\",\n" +
          "      \"category\": 2\n" +
          "    },\n" +
          "    {\n" +
          "      \"name\": \"Pâtes\",\n" +
          "      \"category\": 7\n" +
          "    },\n" +
          "    {\n" +
          "      \"name\": \"Fromage\",\n" +
          "      \"category\": 4\n" +
          "    },\n" +
          "    {\n" +
          "      \"name\": \"Jambon\",\n" +
          "      \"category\": 1\n" +
          "    },\n" +
          "    {\n" +
          "      \"name\": \"Sauce barbecue\",\n" +
          "      \"category\": 5\n" +
          "    },\n" +
          "    {\n" +
          "      \"name\": \"Poivre\",\n" +
          "      \"category\": 6\n" +
          "    },\n" +
          "    {\n" +
          "      \"name\": \"Œufs de caille\",\n" +
          "      \"category\": 3\n" +
          "    },\n" +
          "    {\n" +
          "      \"name\": \"Eau\",\n" +
          "      \"category\": 8\n" +
          "    }\n" +
          "  ],\n" +
          "  \"recipes\": [\n" +
          "    {\n" +
          "      \"name\": \"recette414\",\n" +
          "      \"group\": 0,\n" +
          "      \"difficulty\": 1,\n" +
          "      \"ingredients\": [\n" +
          "        {\n" +
          "          \"index\": 0,\n" +
          "          \"quantity\": \"2\"\n" +
          "        }\n" +
          "      ],\n" +
          "      \"steps\": [\n" +
          "        \"étape 1 de la recette\",\n" +
          "        \"Lorem ipsum dolor sit amet, consectetur adipiscing elit.\",\n" +
          "        \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent dictum tellus ut feugiat pulvinar. In diam lectus, feugiat sed facilisis vitae, faucibus vitae lectus. Maecenas laoreet eleifend metus vel dignissim. Aenean quis orci suscipit, congue orci pulvinar, vulputate orci. Nulla.\",\n" +
          "        \"Nulla.\",\n" +
          "        \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent dictum tellus ut feugiat pulvinar. In diam lectus, feugiat sed facilisis vitae, faucibus vitae lectus. Maecenas laoreet eleifend metus vel dignissim. Aenean quis orci suscipit, congue orci pulvinar, vulputate orci. Nulla.\",\n" +
          "        \"Nulla.\",\n" +
          "        \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent dictum tellus ut feugiat pulvinar. In diam lectus, feugiat sed facilisis vitae, faucibus vitae lectus. Maecenas laoreet eleifend metus vel dignissim. Aenean quis orci suscipit, congue orci pulvinar, vulputate orci. Nulla.\",\n" +
          "        \"Nulla.\"\n" +
          "      ]\n" +
          "    },\n" +
          "    {\n" +
          "      \"name\": \"recette12\",\n" +
          "      \"group\": 0,\n" +
          "      \"difficulty\": 2,\n" +
          "      \"ingredients\": [\n" +
          "        {\n" +
          "          \"index\": 4,\n" +
          "          \"quantity\": \"2\"\n" +
          "        },\n" +
          "        {\n" +
          "          \"index\": 6,\n" +
          "          \"quantity\": \"2\"\n" +
          "        },\n" +
          "        {\n" +
          "          \"index\": 7,\n" +
          "          \"quantity\": \"50 L\"\n" +
          "        }\n" +
          "      ],\n" +
          "      \"steps\": [\n" +
          "        \"étape 1 de la recette\",\n" +
          "        \"Lorem ipsum dolor sit amet, consectetur adipiscing elit.\",\n" +
          "        \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent dictum tellus ut feugiat pulvinar. In diam lectus, feugiat sed facilisis vitae, faucibus vitae lectus. Maecenas laoreet eleifend metus vel dignissim. Aenean quis orci suscipit, congue orci pulvinar, vulputate orci. Nulla.\",\n" +
          "        \"Nulla.\",\n" +
          "        \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent dictum tellus ut feugiat pulvinar. In diam lectus, feugiat sed facilisis vitae, faucibus vitae lectus. Maecenas laoreet eleifend metus vel dignissim. Aenean quis orci suscipit, congue orci pulvinar, vulputate orci. Nulla.\",\n" +
          "        \"Nulla.\",\n" +
          "        \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent dictum tellus ut feugiat pulvinar. In diam lectus, feugiat sed facilisis vitae, faucibus vitae lectus. Maecenas laoreet eleifend metus vel dignissim. Aenean quis orci suscipit, congue orci pulvinar, vulputate orci. Nulla.\",\n" +
          "        \"Nulla.\",\n" +
          "        \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent dictum tellus ut feugiat pulvinar. In diam lectus, feugiat sed facilisis vitae, faucibus vitae lectus. Maecenas laoreet eleifend metus vel dignissim. Aenean quis orci suscipit, congue orci pulvinar, vulputate orci. Nulla.\",\n" +
          "        \"Nulla.\",\n" +
          "        \"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent dictum tellus ut feugiat pulvinar. In diam lectus, feugiat sed facilisis vitae, faucibus vitae lectus. Maecenas laoreet eleifend metus vel dignissim. Aenean quis orci suscipit, congue orci pulvinar, vulputate orci. Nulla.\",\n" +
          "        \"Nulla.\"\n" +
          "      ]\n" +
          "    },\n" +
          "    {\n" +
          "      \"name\": \"recette4\",\n" +
          "      \"group\": 2,\n" +
          "      \"difficulty\": 0,\n" +
          "      \"ingredients\": [\n" +
          "        {\n" +
          "          \"index\": 0,\n" +
          "          \"quantity\": \"2\"\n" +
          "        },\n" +
          "        {\n" +
          "          \"index\": 3,\n" +
          "          \"quantity\": \"50 L\"\n" +
          "        },\n" +
          "        {\n" +
          "          \"index\": 5,\n" +
          "          \"quantity\": \"5 pincées\"\n" +
          "        },\n" +
          "        {\n" +
          "          \"index\": 8,\n" +
          "          \"quantity\": \"2 gallons\"\n" +
          "        }\n" +
          "      ],\n" +
          "      \"steps\": [\n" +
          "        \"étape 1 de la recette\",\n" +
          "        \"Lorem ipsum dolor sit amet\",\n" +
          "        \"Maecenas laoreet\",\n" +
          "        \"Lorem ipsum dolor sit amet\",\n" +
          "        \"Maecenas laoreet\",\n" +
          "        \"Nulla.\"\n" +
          "      ]\n" +
          "    },\n" +
          "    {\n" +
          "      \"name\": \"recette\",\n" +
          "      \"group\": 1,\n" +
          "      \"difficulty\": 1,\n" +
          "      \"ingredients\": [\n" +
          "        {\n" +
          "          \"index\": 2,\n" +
          "          \"quantity\": \"2\"\n" +
          "        },\n" +
          "        {\n" +
          "          \"index\": 1,\n" +
          "          \"quantity\": \"cinq tranches\"\n" +
          "        }\n" +
          "      ],\n" +
          "      \"steps\": [\n" +
          "        \"étape 1 de la recette\"\n" +
          "      ]\n" +
          "    }\n" +
          "  ]\n" +
          "}"
}