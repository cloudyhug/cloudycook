package io.github.cloudyhug.cloudycook.components.addrecipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import io.github.cloudyhug.cloudycook.R
import io.github.cloudyhug.cloudycook.database.RecipeEntry
import io.github.cloudyhug.cloudycook.database.RecipeStepEntry
import io.github.cloudyhug.cloudycook.Database
import kotlinx.android.synthetic.main.activity_add_recipe.*

class AddRecipeActivity : AppCompatActivity(), AddStepDialogFragment.AddStepDialogListener {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_add_recipe)
    
    addStepButton.setOnClickListener {
      // TODO : lancer un AddStepDialogFragment
    }
    addRecipeButton.setOnClickListener {
      //addRecipe()
    }
  }
  
  override fun onDialogPositiveClick(newStep: String) {
    // TODO : ajouter l'étape à la liste
  }
  
  /*private fun addStep() {
    if (editStep.text.isNotBlank()) {
      (stepListFragment as AddStepsFragment).items.add(editStep.text.toString().trim())
    } else {
      Snackbar.make(add_recipe_linearlayout, R.string.step_empty, Snackbar.LENGTH_SHORT)
    }
    editStep.text.clear()
  }*/
  
  /*private fun addRecipe() {
    if (editRecipeName.text.isNotBlank()) {
      val db = Database.db!!
      val recipes = db.recipeEntryDao()
      val steps = db.recipeStepEntryDao()
      val counter = db.counterDao()
      val recipe_id = counter.getRecipeCount() + 1
      counter.incrementRecipes()
      recipes.insert(
        RecipeEntry(
          recipe_id,
          recipeGroupRadioGroup.checkedRadioButtonId,
          editRecipeName.text.toString().trim(),
          difficultyRadioGroup.checkedRadioButtonId
        )
      )
      val items = (stepListFragment as AddStepsFragment).items
      val step_id = counter.getStepCount() + 1
      counter.incrementStepsBy(items.size)
      steps.insertAll(
        *items
          .mapIndexed { i, step -> RecipeStepEntry(step_id + i, recipe_id, i, step) }
          .toTypedArray()
      )
      // TODO : ajouter contenu ingrédients
    } else {
      Snackbar.make(add_recipe_linearlayout, R.string.recipe_name_empty, Snackbar.LENGTH_SHORT)
    }
  }*/
}

/* TODO : bouton ajouter ingrédient charge la liste des ingrédients dispo dans un dialog
          barre de recherche qui filtre les ingrédients
          cliquer sur un ingrédient lance un autre dialog qui demande la quantité
          enfin affichage du dernier dialog avec ingrédient et qté, et confirmation ou non
          
          test fragment android:theme="@android:style/Theme.Holo.Dialog" + nav graph ?
*/