package io.github.cloudyhug.cloudycook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_view_recipe.*

class ViewRecipeActivity : AppCompatActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_view_recipe)
    
    val recipeId = intent.getIntExtra("recipe_id", -1)
  
    // remembers the last clicked recipe, in case the user visits the same recipe several times
    if (ViewRecipeData.recipe == null || ViewRecipeData.recipe!!.id != recipeId) {
      val db = Database.db!!
      ViewRecipeData.recipe = db.recipeEntryDao().findById(recipeId)
      ViewRecipeData.ingredients =
        db.recipeContentEntryDao()
          .getIngredientsOfRecipe(recipeId)
          .map { Pair(db.ingredientEntryDao().findById(it.ingredient_id), it.quantity) }
      ViewRecipeData.steps =
        db.recipeStepEntryDao()
          .findByRecipeId(recipeId)
          .sortedBy { it.step_number }
          .map { it.instruction }
    }
    
    recipeViewTitle.text = ViewRecipeData.recipe?.name
    
    with(tabs) {
      selectTab(getTabAt(0))
      selectFragment(0)
    
      addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {
        }
      
        override fun onTabSelected(tab: TabLayout.Tab?) {
          tab?.let {
            selectFragment(it.position)
          }
        }
      
        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }
      })
    }
  }
  
  private fun selectFragment(index: Int) {
    pushFragment(
      when (index) {
        1 -> ViewRecipeIngredientsFragment()
        2 -> ViewRecipeStepsFragment()
        else -> ViewRecipeInfoFragment()
      }
    )
  }
  
  private fun pushFragment(fragment: Fragment) {
    supportFragmentManager
      .beginTransaction()
      .replace(R.id.root_layout, fragment)
      .commit()
  }
}