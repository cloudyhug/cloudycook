package io.github.cloudyhug.cloudycook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.cloudyhug.cloudycook.R
import io.github.cloudyhug.cloudycook.RecipeDifficulty
import io.github.cloudyhug.cloudycook.RecipeGroup
import io.github.cloudyhug.cloudycook.ViewRecipeData
import kotlinx.android.synthetic.main.fragment_view_recipe_info.*

class ViewRecipeInfoFragment : Fragment() {
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_view_recipe_info, container, false)
  }
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    
    val recipe = ViewRecipeData.recipe!!
    recipeNameDynamicText.text = recipe.name
    recipeGroupDynamicText.text = RecipeGroup.toString(recipe.group)
    recipeDifficultyDynamicText.text = RecipeDifficulty.toString(recipe.difficulty)
  }
}