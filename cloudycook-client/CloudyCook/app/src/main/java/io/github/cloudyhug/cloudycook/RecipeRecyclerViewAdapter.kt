package io.github.cloudyhug.cloudycook

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import io.github.cloudyhug.cloudycook.database.RecipeEntry

// list item: displays a recipe with its name and difficulty
// each clicked item triggers ViewRecipeActivity with the right recipe ID

class RecipeRecyclerViewAdapter(
  private val values: List<RecipeEntry>,
  private val context: Context
) : RecyclerView.Adapter<RecipeRecyclerViewAdapter.ViewHolder>() {
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.recipe_list_item, parent, false)
    return ViewHolder(view)
  }
  
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val item = values[position]
    holder.recipeInfoView.text = item.name
    holder.recipeInfoView.compoundDrawables[0].setTint(
      when (item.difficulty) {
        RecipeDifficulty.Easy -> Color.rgb(0, 204, 102)
        RecipeDifficulty.Medium -> Color.rgb(255, 153, 0)
        else -> Color.rgb(204, 0, 0)
      }
    )
    holder.recipeInfoView.setOnClickListener {
      val intent = Intent(context, ViewRecipeActivity::class.java)
      intent.putExtra("recipe_id", item.id)
      context.startActivity(intent)
    }
  }
  
  override fun getItemCount(): Int = values.size
  
  inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val recipeInfoView: Button = view.findViewById(R.id.recipe_info)
    
    override fun toString(): String {
      return super.toString() + " '" + recipeInfoView.text + "'"
    }
  }
}